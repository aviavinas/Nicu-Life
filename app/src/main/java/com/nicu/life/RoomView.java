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

public class RoomView extends AppCompatActivity {
    private FireStoreDB db;
    private TextView titleVw, priceVw, descVw, qntVw;
    private ImageView img, backBtn, addBtn;
    private Button bookBtn;
    private Long price, qnt = 1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_view);

        db = FireStoreDB.getInstance(this);
        titleVw = findViewById(R.id.title);
        descVw = findViewById(R.id.desc);
        priceVw = findViewById(R.id.price);
        qntVw = findViewById(R.id.qnt);
        img = findViewById(R.id.img);
        backBtn = findViewById(R.id.btn_back);
        addBtn = findViewById(R.id.btn_add);
        bookBtn = findViewById(R.id.btn_book);

        backBtn.setOnClickListener(v -> finish());

        addBtn.setOnClickListener(v -> {
            qntVw.setText("x "+(++qnt)+" month");
        });

        bookBtn.setOnClickListener(v -> {
            Intent checkout = new Intent(getBaseContext(), Checkout.class);
            checkout.putExtra("item", titleVw.getText());
            checkout.putExtra("qnt", qnt);
            checkout.putExtra("amt", (Long)(price * qnt));
            checkout.putExtra("detail", "Room booking for "+qnt+" month");
            startActivity(checkout);
        });

        loadProduct();
    }

    private void loadProduct() {
        String pid = getIntent().getStringExtra("id");
        if(pid!=null && !pid.isEmpty()) {
            db.getDb().collection("rooms").document(pid).get().addOnSuccessListener(doc -> {
                price = doc.getLong("price");
                titleVw.setText(doc.getString("title"));
                descVw.setText(doc.getString("address"));
                priceVw.setText("₹ "+ price+" per month");

                Glide.with(this).load(FireStorage.getInstance().getImgRef(doc.getString("img"))).into(img);
            });
        }
    }
}
