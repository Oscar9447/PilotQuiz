package com.example.oscar.pilotquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Oscar Richardson
 */
public class HomeScreen extends AppCompatActivity {

// declares buttons
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
                // starts the quiz activity, starting at questions defined by mQuestion Number.
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 0;
                finish();
            }




    });



        fuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts the quiz activity, starting at questions defined by mQuestion Number.
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 10;
                finish();
            }

        });

        electricalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts the quiz activity, starting at questions defined by mQuestion Number.
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 20;
                finish();
            }

        });


        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts the quiz activity, starting at questions defined by mQuestion Number.
                startActivity( new Intent(getApplicationContext(),QuizActivity.class));
                QuizActivity.mQuestionNumber = 30;
                finish();
            }

        });



        //Quit Button
        homeQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quits the app
                finish();
                System.exit(0);
            }
        });







    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // creates an option menu at the top of the screen
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // set a logout action in the menu
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        // signs the user out of the firebase Auth
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeScreen.this, UserSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        // sets the activity back to the login screen
    }}