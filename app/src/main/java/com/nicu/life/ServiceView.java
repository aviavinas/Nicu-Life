package com.nicu.life;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nicu.life.FirebaseClass.FireStorage;
import com.nicu.life.FirebaseClass.FireStoreDB;

public class ServiceView extends AppCompatActivity {
    private FireStoreDB db;
    private TextView titleVw, descVw;
    private ImageView imgVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        db = FireStoreDB.getInstance(this);
        titleVw = findViewById(R.id.title);
        descVw = findViewById(R.id.desc);
        imgVw = findViewById(R.id.img);

        showUI();
    }

    private void showUI() {
        String pid = getIntent().getStringExtra("id");
        if(pid!=null && !pid.isEmpty()) {
            db.getDb().collection("services").document(pid).get().addOnSuccessListener(doc -> {
                titleVw.setText(doc.getString("name"));
                descVw.setText(doc.getString("desc"));

                Glide.with(this).load(FireStorage.getInstance().getImgRef(doc.getString("img"))).into(imgVw);
            });
        }
    }
}
