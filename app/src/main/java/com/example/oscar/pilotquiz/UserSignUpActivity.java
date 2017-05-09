package com.example.oscar.pilotquiz;

/**
 * Created by Oscar
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.example.oscar.pilotquiz.R;


public class UserSignUpActivity extends AppCompatActivity {
    // declares variables
    private static final String TAG = UserSignUpActivity.class.getName();
    private Button signUp;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email, password, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);

        signUp = (Button)findViewById(R.id.signUp);
        userName = (EditText)findViewById(R.id.userName);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);


        findViewById(R.id.loginTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the user sign in activity if the user clicks the sign in text
                startActivity(new Intent(UserSignUpActivity.this, UserSignInActivity.class));
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
            }
        };
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strUserName = userName.getText().toString();
                final String strEmail = email.getText().toString();
                final String strPassword = password.getText().toString();
                if(TextUtils.isEmpty(strUserName)) {
                    // checks to see if field is empty, if it is then it displayed a prompt
                    userName.setError("Enter user name");
                    return;
                }

                if(TextUtils.isEmpty(strEmail)) {
                    // checks to see if field is empty, if it is then it displayed a prompt
                    email.setError("Enter email address");
                    return;
                }
                if(TextUtils.isEmpty(strPassword)) {
                    // checks to see if field is empty, if it is then it displayed a prompt
                    password.setError("Enter Password");
                    return;
                }

                // connects to fire base and creates a new user account using mAuth
                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign up fails, display a message to the user. If sign up succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // Displayed message if the sign up failed
                                    Toast.makeText(UserSignUpActivity.this, "Sign Up Failed",
                                            Toast.LENGTH_SHORT).show();
                                    task.getException().printStackTrace();
                                }
                                else{

                                    //After user creation adding user name also for display prospective
                                    // Add the username of the user to their profile
                                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(userName.getText().toString().trim()).build();
                                    mAuth.getCurrentUser().updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                // if the sign up was successful then goes to home screen activity.
                                                Toast.makeText(UserSignUpActivity.this, "Sign up successfully completed",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivity( new Intent(getApplicationContext(),HomeScreen.class));

                                                finish();
                                            }
                                            else{
                                                task.getException().printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}