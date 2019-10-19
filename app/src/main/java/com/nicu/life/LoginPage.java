package com.nicu.life;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class LoginPage extends AppCompatActivity {
    private final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signIn();
    }

    private void signIn() {
        if(FirebaseAuth.getInstance().getCurrentUser()==null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),RC_SIGN_IN);
        } else {
            goHome();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                goHome();
            } else {
                Toast.makeText(this, "Login failed :(", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goHome() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    private void pin(String s) {
        Log.d("LGNX", s);
    }
}
