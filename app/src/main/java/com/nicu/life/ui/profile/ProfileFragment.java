package com.nicu.life.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nicu.life.LoginPage;
import com.nicu.life.R;

public class ProfileFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    private FirebaseAuth mAuth;
    private ImageView avatar;
    private View signout;
    private TextView name, email;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        avatar = getActivity().findViewById(R.id.avatar);
        name = getActivity().findViewById(R.id.fullname);
        email = getActivity().findViewById(R.id.email);
        signout = getActivity().findViewById(R.id.signout);

        name.setText(currentUser.getDisplayName());
        email.setText(currentUser.getEmail());

        Glide.with(getActivity()).load(currentUser.getPhotoUrl()).into(avatar);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getContext(), LoginPage.class);
                getActivity().finish();
                startActivity(login);
            }
        });
    }

    private void pin(String s) {
        Log.d("PRFX", s);
    }
}