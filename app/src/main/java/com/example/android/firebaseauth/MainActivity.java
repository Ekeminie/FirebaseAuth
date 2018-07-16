package com.example.android.firebaseauth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends BaseActivity {

    private EditText email_et, password_et;
    private Button signin, signup;
    private Context getActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize context for this activity
         */
        getActivityContext = this;

        /**
         * View Binding
         */

        email_et = findViewById(R.id.email);
        password_et = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressDialog("Signing Up");
                String email = email_et.getText().toString(); //email
                String password = password_et.getText().toString();//password

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    email_et.setError("Please Enter an Email");
                    password_et.setError("Please enter a six digit password");
                } else {

                    performSignUp(email, password);
                }

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("Signing In");
                String email = email_et.getText().toString(); //email
                String password = password_et.getText().toString();//password

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    email_et.setError("Please Enter an Email");
                    password_et.setError("Please enter a six digit password");
                } else {

                    performSignIn(email, password);
                }
            }
        });

    }

    /**
     * Sign Up User Using Firebase
     *
     * @param email
     * @param password
     */
    private void performSignUp(String email, String password) {
        getFireBaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    hideProgressDialog();
                    signup.setVisibility(View.GONE);

                    /**
                     * if the firebase authentication task result is succesful, we want to do the follwoing
                     */


                } else {
                    hideProgressDialog();
                    String error = task.getException().getMessage();
                    Toast.makeText(getActivityContext, error, Toast.LENGTH_LONG);
                }

            }
        });


    }


    public void performSignIn(String email, String password) {

        getFireBaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    hideProgressDialog();
                    signin.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivityContext, "SinIn Succesfull", Toast.LENGTH_LONG);
                } else {
                    hideProgressDialog();

                    String error = task.getException().getMessage();
                    Toast.makeText(getActivityContext, error, Toast.LENGTH_LONG);
                }

            }
        });
    }


}