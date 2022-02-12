package com.example.searchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int lower = 0;
    private int upper;
    private int middle;
    private int answer;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        EditText input = findViewById(R.id.inputNum);
        if (!(input.getText().toString().matches(""))) {
            upper = Integer.parseInt(input.getText().toString());
        }

        answer = (int) (Math.random() * upper) + 1;

        input.setVisibility(View.INVISIBLE);
        Button startButton = findViewById(R.id.startButton);
        startButton.setText(R.string.buttonRestartText);

        TextView enterText = findViewById(R.id.enterText);
        enterText.setVisibility(View.GONE);
        TextView rules = findViewById(R.id.rulesText);
        rules.setVisibility(View.VISIBLE);

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        leftButton.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
        startButton.setClickable(false);

        TextView scoreNum = findViewById(R.id.scoreNum);
        scoreNum.setVisibility(View.VISIBLE);
        scoreNum.setText(new StringBuilder().append("Score: ").append(0).toString());

        middle = (int) (upper + lower) / 2;

        leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
        rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer > middle) {
                    score += 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                } else if (answer <= middle) {
                    score -= 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                }

                if (middle == lower || middle == upper) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                }

                if (answer == upper) {
                    score += 10;
                    rightButton.setVisibility(View.INVISIBLE);
                    leftButton.setVisibility(View.INVISIBLE);
                    TextView finish = findViewById(R.id.finishText);
                    finish.setVisibility(View.VISIBLE);
                    finish.setText(R.string.finishYes);
                } else if (answer == lower) {
                    score = 0;
                    rightButton.setVisibility(View.INVISIBLE);
                    leftButton.setVisibility(View.INVISIBLE);
                    TextView finish = findViewById(R.id.finishText);
                    finish.setVisibility(View.VISIBLE);
                    finish.setText(R.string.finishNo);
                }
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer > middle) {
                    score -= 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                } else if (answer < middle){
                    score += 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                }

                if (middle == lower || middle == upper) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                }

                if (answer == lower) {
                    score += 10;
                    rightButton.setVisibility(View.INVISIBLE);
                    leftButton.setVisibility(View.INVISIBLE);
                    TextView finish = findViewById(R.id.finishText);
                    finish.setVisibility(View.VISIBLE);
                    finish.setText(R.string.finishYes);
                } else if (answer == upper) {
                    score = 0;
                    rightButton.setVisibility(View.INVISIBLE);
                    leftButton.setVisibility(View.INVISIBLE);
                    TextView finish = findViewById(R.id.finishText);
                    finish.setVisibility(View.VISIBLE);
                    finish.setText(R.string.finishNo);
                }
            }
        });



    }
}
