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

        Thread thread = new Thread(){
            @Override
            public void run(){

                try{
                    sleep(800);
                }
                catch (Exception e) {}

                finally {
                    startActivity( new Intent(getApplicationContext(),HomeScreen.class));

                    finish();
                }

            }
        };
        thread.start();

    }
}
