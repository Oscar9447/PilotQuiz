package com.example.oscar.pilotquiz;

/**
 * Created by Oscar Richardson
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
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.example.oscar.pilotquiz.R;

public class UserSignInActivity extends AppCompatActivity {

    // declares variables
    private static final String TAG = UserSignUpActivity.class.getName();
    private Button signIn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_sign_in);

        findViewById(R.id.signupHere).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the user sign up activity if the user clicks the sign up text
                startActivity(new Intent(UserSignInActivity.this, UserSignUpActivity.class));
                finish();
            }
        });
        signIn = (Button) findViewById(R.id.signIn);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // connects to Firebase User Auth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
            }
        };
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String strEmail = email.getText().toString();
                final String strPassword = password.getText().toString();

                // checks to see if field is empty, if it is then it displayed a prompt
                if(TextUtils.isEmpty(strEmail)) {
                    email.setError("Enter email address");
                    return;
                }
                // checks to see if field is empty, if it is then it displayed a prompt
                if(TextUtils.isEmpty(strPassword)) {
                    password.setError("Enter Password");
                    return;
                }
                // connects to fire base and signs in the user using mAuth
                mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(UserSignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {

                                    // Displays message if sign in failed
                                    task.getException().printStackTrace();
                                    Log.w(TAG, "signInWithEmail", task.getException());
                                   Toast.makeText(UserSignInActivity.this, "Sign In Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    // displayed message if user successfully signs in
                                    Toast.makeText(UserSignInActivity.this, "Signed In",Toast.LENGTH_SHORT).show();
                                    // Changes to the home activity.
                                    startActivity( new Intent(getApplicationContext(),HomeScreen.class));

                                    finish();
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