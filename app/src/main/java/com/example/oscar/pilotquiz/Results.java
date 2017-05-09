package com.example.oscar.pilotquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Oscar on 29/04/2017.
 */

public class Results extends AppCompatActivity {

// declares variables
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
        // displayed the users score out of 10
         userScore.setText("You Scored : "+QuizActivity.mScore+"/10");

        //Quit Button
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                //quits the app
            }
        });


        //HOME Button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),HomeScreen.class));
                finish();
                // goes to home activity
            }
        });









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
        // creates an option menu at the top of the screen
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
        // set a logout action in the menu
    }
    private void logout() {
        // signs the user out of the firebase Auth
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Results.this, UserSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        // sets the activity back to the login screen
    }}