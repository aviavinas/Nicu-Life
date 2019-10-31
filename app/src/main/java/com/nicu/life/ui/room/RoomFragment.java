package com.nicu.life.ui.room;

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
import com.nicu.life.FirebaseClass.FireStoreDB;
import com.nicu.life.R;
import com.nicu.life.Recycler.Recycler;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class RoomFragment extends Fragment {
    private FireStoreDB db;
    private ArrayList<String> items=new ArrayList<>();
    private SpinnerDialog spinnerDialog;
    private TextView locTxt;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locTxt = getActivity().findViewById(R.id.locTxt);
        db = FireStoreDB.getInstance(getActivity());
        Query q1 = db.getDb().collection("rooms");
        Recycler recycler = new Recycler(getActivity());
        recycler.setRoomRecycler(q1, R.id.roomRecycle);

        items.add("Mumbai");
        items.add("Delhi");
        items.add("Bengaluru");
        items.add("Hyderabad");
        items.add("Ahmedabad");
        items.add("Chennai");
        items.add("Kolkata");
        items.add("Surat");
        items.add("Pune");
        items.add("Jaipur");
        items.add("Lucknow");
        items.add("Kanpur");

        spinnerDialog=new SpinnerDialog(getActivity(),items,"Select or Search City","Close Button Text");// With No Animation
        spinnerDialog=new SpinnerDialog(getActivity(),items,"Select or Search City",R.style.DialogAnimations_SmileWindow,"Close Button Text");// With 	Animation

        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(getContext(), "Showing from "+item, Toast.LENGTH_SHORT).show();
                locTxt.setText(item);
            }
        });

        getActivity().findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
    }
}