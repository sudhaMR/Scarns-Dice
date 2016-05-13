package com.example.sudha.dicegame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
//import java.util.logging.Handler;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    int turnScore;
    int computerState;
    int userScore = 0;
    int compScore = 0;
    int diceValue;
    Button roll;
    Button hold;
    Button reset;
    Boolean holdTrue,rollTrue,resetTrue,computerTurn,isOneDie=false;
    TextView userScoreBoard;
    TextView turnScoreBoard;
    TextView compScoreBoard;
    TextView resultBoard;
    ImageView img;
    Random random = new Random();

    public void winOrLose(){
        if(userScore>=100){
            resultBoard.setText("You Win");
        }
        else if(compScore>=100){
            resultBoard.setText("Computer Wins");
        }
    }


    public void rollDie(){
        diceValue = random.nextInt(5) + 1;
        rollTrue = true;
        int id;

        switch (diceValue) {
            case 1: {
                id = getResources().getIdentifier("dice1", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 1;
                isOneDie = true;
                if(rollTrue) {
                    roll.setEnabled(false);
                    turnScore = 0;
                    userScore = userScore + turnScore;
                    rollTrue = false;
                }
                break;
            }
            case 2: {
                id = getResources().getIdentifier("dice2", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 2;
                isOneDie = false;
                break;
            }
            case 3: {
                id = getResources().getIdentifier("dice3", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 3;
                isOneDie = false;
                break;
            }
            case 4: {
                id = getResources().getIdentifier("dice4", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 4;
                isOneDie = false;
                break;
            }
            case 5: {
                id = getResources().getIdentifier("dice5", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 5;
                isOneDie = false;
                break;
            }
            case 6: {
                id = getResources().getIdentifier("dice6", "drawable", getPackageName());
                img.setImageResource(id);
                turnScore = turnScore + 6;
                isOneDie = false;
                break;
            }
        }

        turnScoreBoard.setText(Integer.toString(turnScore));
        winOrLose();
    }

    public int computerPlayTurn(){
        final Handler handler = new Handler();
        if(computerTurn==false){

            return turnScore;
        }
        if(computerTurn){
            rollDie();

        handler.postDelayed(new Runnable() {
            @Override

            public void run() {
                int temp = computerPlayTurn();
                if (turnScore > 20 || isOneDie) {
                    computerTurn = false;
                    temp = computerPlayTurn();
                    compScore = compScore + turnScore;
                    compScoreBoard.setText(Integer.toString(compScore/2));
                    roll.setEnabled(true);
                    turnScore=0;
                    turnScoreBoard.setText(Integer.toString(turnScore));
                    winOrLose();
                    handler.removeCallbacks(this);
                    //Stop();
                    return;
                 }
                }

            }, 2500);

        }
        return 202;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        roll = (Button)findViewById(R.id.rollButton);
        hold = (Button)findViewById(R.id.holdButton);
        reset = (Button)findViewById(R.id.resetButton);

        userScoreBoard = (TextView)findViewById(R.id.user);
        turnScoreBoard = (TextView) findViewById(R.id.turn);
        compScoreBoard = (TextView) findViewById(R.id.computer);
        resultBoard = (TextView) findViewById(R.id.result);

        img = (ImageView) findViewById(R.id.imageView);


        int id = getResources().getIdentifier("dice4","drawable",getPackageName());
        img.setImageResource(id);

        turnScore = 0;
        roll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rollTrue = true;
                rollDie();
            }
        });



        hold. setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                holdTrue = true;
                rollTrue = false;
                computerTurn = true;
                userScore = userScore + turnScore;
                turnScore = 0;
                turnScoreBoard.setText("0");
                userScoreBoard.setText(Integer.toString(userScore));

                final Handler handler = new Handler();

                int temp = computerPlayTurn();

                turnScore = 0;
                roll.setEnabled(true);

            }
        });

        reset. setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //holdTrue = true;
                //rollTrue = false;
               // computerTurn = true;
                //userScore = userScore+turnScore;
                turnScore = 0;
                userScore = 0;
                compScore = 0;
                turnScoreBoard.setText("0");
                userScoreBoard.setText(Integer.toString(userScore));
                compScoreBoard.setText("0");
                roll.setEnabled(true);
            }
        });

    }

}

