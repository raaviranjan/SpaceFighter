package com.example.ravi.spacefighter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity{

    ImageButton buttonPlay,buttonHighScore;
    SharedPreferences sharedPreferences;
    String[] h_Scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonHighScore = (ImageButton) findViewById(R.id.buttonScore);

        h_Scores = new String[5];

        sharedPreferences  = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        h_Scores[0] = String.valueOf(sharedPreferences.getInt("score1",0));
        h_Scores[1] = String.valueOf(sharedPreferences.getInt("score2",0));
        h_Scores[2] = String.valueOf(sharedPreferences.getInt("score3",0));
        h_Scores[3] = String.valueOf(sharedPreferences.getInt("score4",0));
        h_Scores[4] = String.valueOf(sharedPreferences.getInt("score5",0));

        //adding a click listener
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //starting game activity
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        buttonHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("High Scores");
                builder.setItems(h_Scores, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
