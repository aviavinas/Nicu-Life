package com.nicu.life.Recycler;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class Recycler {
    private Activity activity;

    public Recycler(Activity a) {
        activity = a;
    }

    public void setCategoryRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Category> options = new FirestoreRecyclerOptions.Builder<Card.Category>().setQuery(query, Card.Category.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.Category adaptor = new Adaptor.Category(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

//        adaptor.setOnItemClickListener((documentSnapshot, pos) -> {
//            Card.Category category = documentSnapshot.toObject(Card.Category.class);
//        });

        adaptor.startListening();
    }

    public void setProductRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Product> options = new FirestoreRecyclerOptions.Builder<Card.Product>().setQuery(query, Card.Product.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.Product adaptor = new Adaptor.Product(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

//        adaptor.setOnItemClickListener((documentSnapshot, pos) -> {
//            Card.Product product = documentSnapshot.toObject(Card.Product.class);
//        });

        adaptor.startListening();
    }

    private void pin(String msg) {
        Log.d("RCMGX", msg);
    }
}