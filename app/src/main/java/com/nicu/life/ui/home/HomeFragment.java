package com.nicu.life.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nicu.life.FirebaseClass.FireStoreDB;
import com.nicu.life.R;
import com.nicu.life.Recycler.Recycler;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class HomeFragment extends Fragment {
    private TextView locTxt;
    private FireStoreDB db;
    private ArrayList<String> items=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locTxt = getActivity().findViewById(R.id.locTxt);
        db = FireStoreDB.getInstance(getActivity());
        runQuery(null);
        locationSel();
    }

    private void runQuery(String loc) {
        Recycler recycler = new Recycler(getActivity());
        Query q1 = db.getDb().collection("stores");
        Query q2 = db.getDb().collection("product");
        Query q3 = db.getDb().collection("product");

        if(loc!=null && !loc.isEmpty()) {
            q2 = q2.whereEqualTo("area", loc);
            q3 = q3.whereEqualTo("area", loc);
        }

        recycler.setShopRecycler(q1, R.id.storeRecycle);
        recycler.setProductRecycler(q2, R.id.prodRecycle);
        recycler.setProductMenuRecycler(q3, R.id.prodMenuRecycle);
    }

    private void locationSel() {
        final SpinnerDialog spinnerDialog;

        db.getDb().collection("area").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    items.add(doc.getString("name"));
                }
            }
        });

        spinnerDialog = new SpinnerDialog(getActivity(),items,"Enter location",R.style.DialogAnimations_SmileWindow,"Close");

        spinnerDialog.setCancellable(true);
        spinnerDialog.setShowKeyboard(true);

        spinnerDialog.bindOnSpinerListener((item, position) -> {
            Toast.makeText(getContext(), "Showing from "+item, Toast.LENGTH_SHORT).show();
            switchLocation(item);
        });

        getActivity().findViewById(R.id.location1).setOnClickListener(v -> spinnerDialog.showSpinerDialog());
    }

    private void switchLocation(String loc) {
        locTxt.setText(loc);
        runQuery(loc);
    }
}