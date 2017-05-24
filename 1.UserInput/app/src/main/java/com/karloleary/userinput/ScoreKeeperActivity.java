package com.karloleary.userinput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by karl on 22/03/17.
 */

public class ScoreKeeperActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorekeeper);
    }


    // ****************************************************


    public void addThreePointsTeamA(View v) {
        scoreTeamA += 3;
        display();
    }

    public void addTwoPointsTeamA(View v) {
        scoreTeamA += 2;
        display();
    }

    public void addOnePointTeamA(View v) {
        scoreTeamA += 1;
        display();
    }


    // ****************************************************


    public void addThreePointsTeamB(View v) {
        scoreTeamB += 3;
        display();
    }

    public void addTwoPointsTeamB(View v) {
        scoreTeamB += 2;
        display();
    }

    public void addOnePointTeamB(View v) {
        scoreTeamB += 1;
        display();
    }


    // ****************************************************


    public void reset(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        display();
    }


    // ****************************************************


    /**
     * This method displays the given text on the screen.
     */
    private void display() {
        TextView aView = (TextView) findViewById(R.id.team_a_score_tw);
        aView.setText(Integer.toString(scoreTeamA));

        TextView bView = (TextView) findViewById(R.id.team_b_score_tw);
        bView.setText(Integer.toString(scoreTeamB));
    }
}
