package com.example.oscar.pilotquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {


    private Button ApuButton;
    private Button fuelButton;
    private Button electricalButton;
    private Button navigationButton;
    private Button homeQuitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        ApuButton = (Button) findViewById(R.id.ApuButton);
        fuelButton = (Button) findViewById(R.id.fuelButton);
        electricalButton = (Button) findViewById(R.id.electricalButton);
        navigationButton = (Button) findViewById(R.id.navigationButton);
        homeQuitButton = (Button) findViewById(R.id.homeQuitButton);



        ApuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 0;
                finish();
            }




    });



        fuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 10;
                finish();
            }

        });

        electricalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 20;
                finish();
            }

        });


        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 30;
                finish();
            }

        });



        //Quit Button
        homeQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });




    }}
