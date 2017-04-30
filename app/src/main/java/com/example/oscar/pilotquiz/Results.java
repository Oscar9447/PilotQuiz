package com.example.oscar.pilotquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Oscar on 29/04/2017.
 */

public class Results extends AppCompatActivity {


    private Button quitButton;
    private Button homeButton;
    private TextView userScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);


        quitButton = (Button) findViewById(R.id.quitButton);
        homeButton = (Button) findViewById(R.id.homeButton);
        TextView userScore = (TextView)findViewById(R.id.userScore);

         userScore.setText("You Scored : "+QuizActivity.mScore+"/10");

        //Quit Button
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        //HOME Button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),HomeScreen.class));
                finish();
            }
        });









    }}


