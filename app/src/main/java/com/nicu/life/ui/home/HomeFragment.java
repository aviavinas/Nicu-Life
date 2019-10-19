package com.nicu.life.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.firestore.Query;
import com.nicu.life.FirebaseClass.FireStoreDB;
import com.nicu.life.R;
import com.nicu.life.Recycler.Recycler;

public class HomeFragment extends Fragment {
    private FireStoreDB db;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FireStoreDB.getInstance(getActivity());

        Recycler recycler = new Recycler(getActivity());

        Query q1 = db.getDb().collection("category");
        Query q2 = db.getDb().collection("product");

        recycler.setCategoryRecycler(q1, R.id.catRecycle);
        recycler.setProductRecycler(q2, R.id.prodRecycle);
    }
}