package com.example.android.firebaseauth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class BaseActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Initialising Progress dialog
         */
        pd = new ProgressDialog(this);


        /**
         * Firebase Authentication Instance
         */

        mAuth = FirebaseAuth.getInstance();
    }

    public void showProgressDialog(String message) {

        pd.setMessage(message);
        pd.setCancelable(false);
        pd.show();
    }

    public void hideProgressDialog() {
        pd.hide();
    }

    public FirebaseAuth getFireBaseAuth() {
        return mAuth;
    }

}
