package com.example.oscar.pilotquiz;

/**
 * Created by Oscar Richardson
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.rgb;
import static java.lang.Thread.sleep;

public class QuizActivity extends AppCompatActivity {

    public static int mQuestionNumber;
    private TextView mScoreView;
    private TextView mQuestion;
    private int mQuestionCount = 0;
    static boolean calledAlready = false;
    //Buttons to hold questions
    private Button mButtonChoice1, mButtonChoice2, mButtonChoice3, mButtonQuit;
    // storing user score
    public static int mScore = 0;
    private String mAnswer;



    private DatabaseReference mQuestionRef, mChoice1Ref, mChoice2Ref, mChoice3Ref,  mAnswerRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //checking to see is connection to Firebase database has already been called if not its then called.
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }




        mScoreView = (TextView)findViewById(R.id.score);
        mQuestion = (TextView) findViewById(R.id.question);

        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonQuit =  (Button) findViewById(R.id.quit);

        //resets score
        mScore = 0;
        // calls the update question method go get questions from the database
        updateQuestion();

        //Button 1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When button is clicked it checks to see if the answer on button is the correct one
                if(mButtonChoice1.getText().equals(mAnswer)){
                    //adds point to score if correct
                    mScore = mScore + 1;
                    updateScore(mScore);


                    //set button colour if correct and pauses0 .6 seconds
                    mButtonChoice1.setBackgroundColor(Color.GREEN);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                            //sets button color back to default and updates the question.
                            mButtonChoice1.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();



                }else{
                    //answer is incorrect. set button colour  and pauses 0.6 seconds
                    mButtonChoice1.setBackgroundColor(Color.RED);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                            //sets button color back to default and updates the question.
                            mButtonChoice1.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();
                }
            }
        });
        //Button1


        //Button 2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When button is clicked it checks to see if the answer on button is the correct one
                if(mButtonChoice2.getText().equals(mAnswer)){
                    //adds point to score if correct
                    mScore = mScore + 1;
                    updateScore(mScore);

                    //set button colour if correct and pauses0 .6 seconds
                    mButtonChoice2.setBackgroundColor(Color.GREEN);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                        //sets button color back to default and updates the question.
                            mButtonChoice2.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();
                }else{
                    //answer is incorrect. set button colour  and pauses0 .6 seconds
                    mButtonChoice2.setBackgroundColor(Color.RED);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                            //sets button color back to default and updates the question.
                            mButtonChoice2.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();
                }
            }
        });
        //Button2

        //Button 3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When button is clicked it checks to see if the answer on button is the correct one
                if(mButtonChoice3.getText().equals(mAnswer)){
                    //adds point to score if correct
                    mScore = mScore + 1;
                    updateScore(mScore);
                    //set button colour if correct and pauses0 .6 seconds
                    mButtonChoice3.setBackgroundColor(Color.GREEN);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                            //sets button color back to default and updates the question.
                            mButtonChoice3.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();
                }else{
                    //answer is incorrect. set button colour  and pauses0 .6 seconds
                    mButtonChoice3.setBackgroundColor(Color.RED);
                    new CountDownTimer(600, 6) {

                        @Override
                        public void onTick(long arg0) {


                        }

                        @Override
                        public void onFinish() {
                            //sets button color back to default and updates the question.
                            mButtonChoice3.setBackgroundColor(Color.BLUE);
                            updateQuestion();
                        }
                    }.start();
                }
            }
        });
        //Button3



        //Back Button
        mButtonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // goes back to home screen
                startActivity(new Intent(QuizActivity.this, HomeScreen.class));
                finish();
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
            // set a logout action in the menu
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        // signs the user out of the firebase Auth
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(QuizActivity.this, UserSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        // sets the activity back to the login screen
    }


    private void updateScore(int score){
        mScoreView.setText("" + mScore);
    }
    // update the score on the screen

    private void updateQuestion() {
        //gets an instance from the database, depending on the question number
    mQuestionRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/question");

    mQuestionRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String question = dataSnapshot.getValue(String.class);
            //set buttons colour back to blue when any data is changed
            mButtonChoice1.setBackgroundColor(rgb(0, 145, 234));
            mButtonChoice2.setBackgroundColor(rgb(0, 145, 234));
            mButtonChoice3.setBackgroundColor(rgb(0, 145, 234));
            // sets the text for the question
            mQuestion.setText(question);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
        //Gets the first answer choice from the database
        mChoice1Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/choice1");
        mChoice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                // sets the answer data received from the database to the text on the button.
                mButtonChoice1.setText(choice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mChoice2Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/choice2");
        mChoice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                // sets the answer data received from the database to the text on the button.
                mButtonChoice2.setText(choice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mChoice3Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/choice3");
        mChoice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                // sets the answer data received from the database to the text on the button.
                mButtonChoice3.setText(choice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Get correct answer from database.
        mAnswerRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/answer");
        mAnswerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAnswer = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // increments question count
        mQuestionNumber ++;
        if (mQuestionCount == 10){
            // Stops the quiz after 10 questions answered and displayed the result activity.
            startActivity( new Intent(getApplicationContext(),Results.class));
                        finish();

        }else{
            // increments question count
            mQuestionCount ++;
        }



    }


}
