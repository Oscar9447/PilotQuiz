package com.example.oscar.pilotquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Oscar
 */

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Pauses the app so the splash screen shows for 0.8 seconds.
        Thread thread = new Thread(){
            @Override
            public void run(){

                try{
                    sleep(800);
                }
                catch (Exception e) {}

                finally {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    if (auth.getCurrentUser() != null) {
                        // Goes to home avtivity is user is still logged in
                        startActivity(new Intent(SplashScreen.this, HomeScreen.class));
                        finish();
                        // allows for persistent login
                    }
                    else
                    startActivity( new Intent(getApplicationContext(),UserSignInActivity.class));
                    // sets the activity to the User sign in screen if not already signed in.

                    finish();
                }

            }
        };
        thread.start();

    }
}
