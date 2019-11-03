package com.nicu.life;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nicu.life.FirebaseClass.FireStorage;
import com.nicu.life.FirebaseClass.FireStoreDB;

public class ServiceView extends AppCompatActivity {
    private FireStoreDB db;
    private TextView titleVw, descVw, priceVw;
    private ImageView imgVw;
    private Button btnBook;
    private Long price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        db = FireStoreDB.getInstance(this);
        titleVw = findViewById(R.id.title);
        descVw = findViewById(R.id.desc);
        priceVw = findViewById(R.id.price);
        imgVw = findViewById(R.id.img);
        btnBook = findViewById(R.id.btn_book);

        btnBook.setOnClickListener(v -> {
            Intent checkout = new Intent(getBaseContext(), Checkout.class);
            checkout.putExtra("item", titleVw.getText());
            checkout.putExtra("qnt", 1);
            checkout.putExtra("amt", price);
            checkout.putExtra("detail", "Service Booking");
            startActivity(checkout);
        });

        showUI();
    }

    private void showUI() {
        String pid = getIntent().getStringExtra("id");
        if(pid!=null && !pid.isEmpty()) {
            db.getDb().collection("services").document(pid).get().addOnSuccessListener(doc -> {
                price = doc.getLong("price");
                titleVw.setText(doc.getString("name"));
                descVw.setText(doc.getString("desc"));
                priceVw.setText("₹ "+price);

                Glide.with(this).load(FireStorage.getInstance().getImgRef(doc.getString("img"))).into(imgVw);
            });
        }
    }
}
