package com.example.oscar.pilotquiz;

import android.content.Intent;
import android.graphics.Color;
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

public class QuizActivity extends AppCompatActivity {

    public static int mQuestionNumber;
    private TextView mScoreView;
    private TextView mQuestion;
    private int mQuestionCount = 0;
    static boolean calledAlready = false;

    private Button mButtonChoice1, mButtonChoice2, mButtonChoice3, mButtonQuit;

    public static int mScore = 0;
    private String mAnswer;



    private DatabaseReference mQuestionRef, mChoice1Ref, mChoice2Ref, mChoice3Ref,  mAnswerRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //if already called
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
        updateQuestion();

        //Button 1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText().equals(mAnswer)){

                    mScore = mScore + 1;
                    updateScore(mScore);
                    //set button colour if correct
                    mButtonChoice1.setBackgroundColor(Color.GREEN);

                       updateQuestion();
                }else{
                    //set button colour
                    mButtonChoice1.setBackgroundColor(Color.RED);
                    updateQuestion();
                }
            }
        });
        //Button1


        //Button 2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    //set button colour if correct
                    mButtonChoice2.setBackgroundColor(Color.GREEN);


                    updateQuestion();
                }else{
                    //set button colour
                    mButtonChoice2.setBackgroundColor(Color.RED);

                    updateQuestion();
                }
            }
        });
        //Button2

        //Button 3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    //set button colour if correct
                    mButtonChoice3.setBackgroundColor(Color.GREEN);


                    updateQuestion();
                }else{
                    //set button colour
                    mButtonChoice3.setBackgroundColor(Color.RED);

                    updateQuestion();
                }
            }
        });
        //Button3



        //Quit Button
        mButtonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(QuizActivity.this, UserSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private void updateScore(int score){
        mScoreView.setText("" + mScore);
    }

    private void updateQuestion() {
    mQuestionRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/question");

    mQuestionRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String question = dataSnapshot.getValue(String.class);
            //set buttons colour back to blue
            mButtonChoice1.setBackgroundColor(rgb(0, 145, 234));
            mButtonChoice2.setBackgroundColor(rgb(0, 145, 234));
            mButtonChoice3.setBackgroundColor(rgb(0, 145, 234));

            mQuestion.setText(question);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
        mChoice1Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pilotquiz.firebaseio.com/"+ mQuestionNumber +"/choice1");
        mChoice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
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
                mButtonChoice3.setText(choice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        mQuestionNumber ++;
        if (mQuestionCount == 10){
            startActivity( new Intent(getApplicationContext(),Results.class));
                        finish();

        }else{
            mQuestionCount ++;
        }



    }


}
