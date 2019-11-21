package com.nicu.life;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.nicu.life.FirebaseClass.FireStoreDB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Checkout extends AppCompatActivity {
    private TextView orderId, item, date, qnt, amt, detail;
    private Map<String, Object> order = new HashMap<>();
    private ProgressBar progressBar;
    private Button payBtn;
    private EditText delvAdd;
    private ImageView tick, back;
    private FireStoreDB db;
    private SharedPreferences session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        db = FireStoreDB.getInstance(this);
        session = getSharedPreferences("userSession", MODE_PRIVATE);
        progressBar = findViewById(R.id.spin_kit);
        progressBar.bringToFront();
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        orderId = findViewById(R.id.orderNo);
        item = findViewById(R.id.item);
        amt = findViewById(R.id.amt);
        qnt = findViewById(R.id.qnt);
        detail = findViewById(R.id.detail);
        date = findViewById(R.id.date);
        payBtn = findViewById(R.id.payBtn);
        tick = findViewById(R.id.tick);
        back = findViewById(R.id.back);
        delvAdd = findViewById(R.id.delvAdd);

        back.setOnClickListener(v -> finish());
        payBtn.setOnClickListener(v -> saveOrder());

        showOrder();
    }

    private void showOrder() {
        order.put("orderId", getOrderNo());
        order.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        order.put("item", getIntent().getStringExtra("item"));
        order.put("store", getIntent().getStringExtra("store"));
        order.put("qnt", getIntent().getLongExtra("qnt", 1));
        order.put("amt", getIntent().getLongExtra("amt", 0));
        order.put("detail", getIntent().getStringExtra("detail"));
        order.put("status", "pending");
        order.put("date", new Date());

        orderId.setText("#"+order.get("orderId").toString());
        item.setText(order.get("item").toString());
        amt.setText("₹ "+order.get("amt").toString());
        qnt.setText(order.get("qnt").toString());
        detail.setText(order.get("detail").toString());
        date.setText(new SimpleDateFormat("HH:mm:ss dd MMM yyyy").format(new Date()));
        displayAddress();
    }

    private void saveOrder() {
        if (delvAdd.getText().equals("")) {
            Toast.makeText(this, "Address can't be empty :(", Toast.LENGTH_LONG).show();
            return;
        }

        saveAddress();
        progressBar.setVisibility(View.VISIBLE);
        db.getDb().collection("orders").add(order).addOnSuccessListener(documentReference -> {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(), "Order completed :)", Toast.LENGTH_LONG).show();
            showSuccess();
        }).addOnFailureListener(e -> {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(), "Failed to complete order :(", Toast.LENGTH_LONG).show();
        });
    }

    private void displayAddress() {
        String addr = session.getString("delvAdd", null);
        if(addr != null) {
            delvAdd.setText(addr);
        }
    }

    private void saveAddress() {
        delvAdd.clearFocus();
        findViewById(R.id.paymentCard).setVisibility(View.GONE);
        String addr = delvAdd.getText().toString();
        order.put("address", addr);

        SharedPreferences.Editor editor = session.edit();
        editor.putString("delvAdd", addr);
        editor.apply();
    }

    private void showSuccess() {
        payBtn.setVisibility(View.GONE);
        tick.setVisibility(View.VISIBLE);
        tick.animate().scaleX(0.3F).scaleY(0.3F);
        tick.animate().setStartDelay(100).scaleX(1.2F).scaleY(1.2F);
    }

    private Long getOrderNo() {
        Random r = new Random( System.currentTimeMillis() );
        return Long.valueOf(100000 + r.nextInt(200000));
    }
}
