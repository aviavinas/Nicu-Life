package com.nicu.life;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;
import com.nicu.life.FirebaseClass.FireStoreDB;
import com.nicu.life.Recycler.Recycler;

public class ViewOrders extends AppCompatActivity {
    private FireStoreDB db;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        db = FireStoreDB.getInstance(this);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        Recycler recycler = new Recycler(this);
        Query q = db.getDb().collection("orders").whereEqualTo("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        recycler.setOrderRecycler(q, R.id.orderRecycle);
    }
}
