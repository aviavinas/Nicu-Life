package com.nicu.life.Recycler;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
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

        adaptor.startListening();
    }

    public void setProductMenuRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Product> options = new FirestoreRecyclerOptions.Builder<Card.Product>().setQuery(query, Card.Product.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.ProductMenu adaptor = new Adaptor.ProductMenu(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

        adaptor.startListening();
    }

    public void setRoomRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Room> options = new FirestoreRecyclerOptions.Builder<Card.Room>().setQuery(query, Card.Room.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.Room adaptor = new Adaptor.Room(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

        adaptor.startListening();
    }

    public void setServiceRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Service> options = new FirestoreRecyclerOptions.Builder<Card.Service>().setQuery(query, Card.Service.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.Service adaptor = new Adaptor.Service(options);

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.setAdapter(adaptor);

        adaptor.startListening();
    }

    public void setOrderRecycler(Query query, int recyclerViewId) {
        FirestoreRecyclerOptions<Card.Order> options = new FirestoreRecyclerOptions.Builder<Card.Order>().setQuery(query, Card.Order.class).build();

        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        Adaptor.Order adaptor = new Adaptor.Order(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

        adaptor.startListening();
    }

    private void pin(String msg) {
        Log.d("RCMGX", msg);
    }
}