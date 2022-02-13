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

    public void initialize() {
        lower = 0;
        middle = 0;
        score = 0;

        Button changeRange = findViewById(R.id.changeRange);
        changeRange.setVisibility(View.GONE);

        TextView finish = findViewById(R.id.finishText);
        if(finish.getVisibility() == View.VISIBLE) {
            finish.setVisibility(View.GONE);
        }

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
        TextView answerNum = findViewById(R.id.answerNum);
        scoreNum.setVisibility(View.VISIBLE);
        answerNum.setVisibility(View.INVISIBLE);
        scoreNum.setText(new StringBuilder().append("Score: ").append(0).toString());
        answerNum.setText(new StringBuilder().append("Answer: ").append(answer).toString());
    }

    public void startGame(View view) {
        EditText input = findViewById(R.id.inputNum);
        if (!(input.getText().toString().matches(""))) {
            upper = Integer.parseInt(input.getText().toString());
        }

        answer = (int) (Math.random() * upper) + 1;

        input.setVisibility(View.INVISIBLE);
        initialize();

        middle = (int) (upper + lower) / 2;

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
        rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));

        TextView scoreNum = findViewById(R.id.scoreNum);
        TextView answerNum = findViewById(R.id.answerNum);
        Button startButton = findViewById(R.id.startButton);
        Button changeButton = findViewById(R.id.changeRange);

        TextView finish = findViewById(R.id.finishText);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((upper - lower) == 1) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                    middle = -1;
                    if (answer == upper) {
                        score += 10;
                        rightButton.setVisibility(View.INVISIBLE);
                        leftButton.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        finish.setText(R.string.finishYes);
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                        startButton.setClickable(true);
                        answerNum.setVisibility(View.VISIBLE);
                        changeButton.setVisibility(View.VISIBLE);
                    } else {
                        score -= 10;
                        rightButton.setVisibility(View.INVISIBLE);
                        leftButton.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        finish.setText(R.string.finishNo);
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                        startButton.setClickable(true);
                        answerNum.setVisibility(View.VISIBLE);
                        changeButton.setVisibility(View.VISIBLE);
                    }
                } else if ((upper - lower) == 2) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    if (answer >= middle) {
                        score += 10;
                        lower = middle;
                        leftButton.setText(new StringBuilder().append(lower));
                        rightButton.setText(new StringBuilder().append(upper));
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                    }
                } else if (answer >= middle) {
                    score += 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                } else if (answer < middle) {
                    score -= 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                }

                if (lower == middle) {
                    leftButton.setText(new StringBuilder().append(lower));
                } else if (middle == upper) {
                    rightButton.setText(new StringBuilder().append(upper));
                }
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((upper - lower) == 1) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                    middle = -1;
                    if (answer == lower) {
                        score += 10;
                        rightButton.setVisibility(View.INVISIBLE);
                        leftButton.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        finish.setText(R.string.finishYes);
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                        startButton.setClickable(true);
                        answerNum.setVisibility(View.VISIBLE);
                        changeButton.setVisibility(View.VISIBLE);
                    } else {
                        score -= 10;
                        rightButton.setVisibility(View.INVISIBLE);
                        leftButton.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        finish.setText(R.string.finishNo);
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                        startButton.setClickable(true);
                        answerNum.setVisibility(View.VISIBLE);
                        changeButton.setVisibility(View.VISIBLE);
                    }
                } else if ((upper - lower) == 2) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    if (answer == lower && middle != answer) {
                        score += 10;
                        rightButton.setVisibility(View.INVISIBLE);
                        leftButton.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        finish.setText(R.string.finishYes);
                        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                    }
                } else if (answer >= middle) {
                    score -= 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                } else if (answer < middle) {
                    score += 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
                }

                if (lower == middle) {
                    leftButton.setText(new StringBuilder().append(lower));
                } else if (middle == upper) {
                    rightButton.setText(new StringBuilder().append(upper));
                }

            }
        });

    }

    public void changeRange(View view) {

        //Returning back to initial view
        TextView finish = findViewById(R.id.finishText);
        if(finish.getVisibility() == View.VISIBLE) {
            finish.setVisibility(View.GONE);
        }

        TextView enterText = findViewById(R.id.enterText);
        enterText.setVisibility(View.VISIBLE);
        TextView rules = findViewById(R.id.rulesText);
        rules.setVisibility(View.GONE);

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        leftButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);

        TextView scoreNum = findViewById(R.id.scoreNum);
        TextView answerNum = findViewById(R.id.answerNum);
        scoreNum.setVisibility(View.INVISIBLE);
        answerNum.setVisibility(View.INVISIBLE);

        EditText input = findViewById(R.id.inputNum);
        input.setVisibility(View.VISIBLE);
        if (!(input.getText().toString().matches(""))) {
            upper = Integer.parseInt(input.getText().toString());
        }

        Button changeRange = findViewById(R.id.changeRange);
        changeRange.setVisibility(View.INVISIBLE);

    }
}
