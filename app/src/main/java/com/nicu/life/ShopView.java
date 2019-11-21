package com.nicu.life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.Query;
import com.nicu.life.FirebaseClass.FireStoreDB;
import com.nicu.life.Recycler.Recycler;

public class ShopView extends AppCompatActivity {
    private ImageView backBtn;
    private FireStoreDB db;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_view);

        db = FireStoreDB.getInstance(this);
        backBtn = findViewById(R.id.btn_back);
        title = findViewById(R.id.title);

        backBtn.setOnClickListener(v -> finish());
        title.setText(getIntent().getStringExtra("name"));

        Recycler recycler = new Recycler(this);
        Query q1 = db.getDb().collection("product");
        recycler.setProdGridRecycler(q1, R.id.prodRecycle);
    }
}
