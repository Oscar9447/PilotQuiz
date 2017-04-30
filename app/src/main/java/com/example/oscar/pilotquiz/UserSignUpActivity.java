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


        ((TextView)findViewById(R.id.loginTextView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                   // Toast.makeText(UserSignUpActivity.this, "Signed In", Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                   // Toast.makeText(UserSignUpActivity.this, "Signed Out", Toast.LENGTH_LONG).show();
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
                    userName.setError("Enter user name");
                    return;
                }

                if(TextUtils.isEmpty(strEmail)) {
                    email.setError("Enter email address");
                    return;
                }
                if(TextUtils.isEmpty(strPassword)) {
                    password.setError("Enter Password");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(UserSignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    task.getException().printStackTrace();
                                }
                                else{

                                    //After user creation adding user name also for display prospective
                                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(userName.getText().toString().trim()).build();
                                    mAuth.getCurrentUser().updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
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