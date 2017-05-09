package com.example.oscar.pilotquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
                    startActivity( new Intent(getApplicationContext(),UserSignInActivity.class));
                    // sets the activity to the home screen when the timer has counted down.

                    finish();
                }

            }
        };
        thread.start();

    }
}
