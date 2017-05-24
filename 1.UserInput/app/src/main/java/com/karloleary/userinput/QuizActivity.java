package com.karloleary.userinput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void results(View v) {
        EditText nameEditText       = (EditText)findViewById(R.id.quiz_name);
        TextView resultsTextView    = (TextView)findViewById(R.id.quiz_results);

        String op = getString(R.string.quiz_results, nameEditText.getText(), getScore(), 5);
        resultsTextView.setVisibility(View.VISIBLE);
        resultsTextView.setText(op);
    }

    public void reset(View v) {
        TextView resultsTextView = (TextView)findViewById(R.id.quiz_results);
        resultsTextView.setVisibility(View.GONE);
        resultsTextView.setText("");

        CheckBox question1CheckBox = (CheckBox)findViewById(R.id.quiz_question1_checkbox);
        question1CheckBox.setChecked(false);
        CheckBox question2CheckBox = (CheckBox)findViewById(R.id.quiz_question2_checkbox);
        question2CheckBox.setChecked(false);
        CheckBox question3CheckBox = (CheckBox)findViewById(R.id.quiz_question3_checkbox);
        question3CheckBox.setChecked(false);
        CheckBox question4CheckBox = (CheckBox)findViewById(R.id.quiz_question4_checkbox);
        question4CheckBox.setChecked(false);
        CheckBox question5CheckBox = (CheckBox)findViewById(R.id.quiz_question5_checkbox);
        question5CheckBox.setChecked(false);
    }

    private int getScore() {
        int score = 0;
        CheckBox question1CheckBox = (CheckBox)findViewById(R.id.quiz_question1_checkbox);
        if (question1CheckBox.isChecked())
            score++;

        CheckBox question2CheckBox = (CheckBox)findViewById(R.id.quiz_question2_checkbox);
        if (question2CheckBox.isChecked())
            score++;

        CheckBox question3CheckBox = (CheckBox)findViewById(R.id.quiz_question3_checkbox);
        if (question3CheckBox.isChecked())
            score++;

        CheckBox question4CheckBox = (CheckBox)findViewById(R.id.quiz_question4_checkbox);
        if (question4CheckBox.isChecked())
            score++;

        CheckBox question5CheckBox = (CheckBox)findViewById(R.id.quiz_question5_checkbox);
        if (question5CheckBox.isChecked())
            score++;

        return score;
    }
}
