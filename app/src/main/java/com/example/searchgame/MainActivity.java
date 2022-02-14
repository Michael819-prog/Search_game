package com.example.searchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    public void checkTextSize() {
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);

        if (upper < 20) {
            rightButton.setTextSize(36);
            leftButton.setTextSize(36);
        } else if(upper >= 20 && upper < 100) {
            rightButton.setTextSize(35);
            leftButton.setTextSize(35);
        } else if (upper >= 100 && upper < 1000) {
            rightButton.setTextSize(28);
            leftButton.setTextSize(28);
        } else if (upper >= 1000 && upper < 10000) {
            rightButton.setTextSize(22);
            leftButton.setTextSize(22);
        } else if (upper >= 10000 && upper < 100000) {
            rightButton.setTextSize(18);
            leftButton.setTextSize(18);
        } else if (upper >= 100000) {
            rightButton.setTextSize(14);
            leftButton.setTextSize(14);
        }
    }

    public void finalCondition(boolean check) {

        //setting some variables
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        TextView scoreNum = findViewById(R.id.scoreNum);
        TextView answerNum = findViewById(R.id.answerNum);
        Button startButton = findViewById(R.id.startButton);
        Button changeButton = findViewById(R.id.changeRange);
        TextView finish = findViewById(R.id.finishText);

        //showing final view
        rightButton.setVisibility(View.INVISIBLE);
        leftButton.setVisibility(View.INVISIBLE);
        finish.setVisibility(View.VISIBLE);
        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());
        startButton.setClickable(true);
        answerNum.setVisibility(View.VISIBLE);
        changeButton.setVisibility(View.VISIBLE);

        answerNum.setText(new StringBuilder().append("Answer: ").append(answer).toString());
        answerNum.setTextColor(getResources().getColor(R.color.buttonDarkColor));

        //if gamer found the right number
        if (check) {
            finish.setText(R.string.finishYes);
            finish.setTextColor(getResources().getColor(R.color.blue));
        } else {
            finish.setText(R.string.finishNo);
            finish.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void changeShownText(boolean textColor) {

        //setting some variables
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        TextView scoreNum = findViewById(R.id.scoreNum);
        TextView answerNum = findViewById(R.id.answerNum);

        leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
        rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
        scoreNum.setText(new StringBuilder().append("Score: ").append(score).toString());

        answerNum.setVisibility(View.VISIBLE);

        if (textColor) {
            answerNum.setText(R.string.correctAnswer);
            answerNum.setTextColor(getResources().getColor(R.color.blue));
        } else {
            answerNum.setText(R.string.wrongAnswer);
            answerNum.setTextColor(getResources().getColor(R.color.red));
        }

    }

    public void checkStartInput(View view) {

        EditText input = findViewById(R.id.inputNum);

        if (!(input.getText().toString().matches(""))) {
            startGame(view);
        }
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

        //Checking size of text (responsive UI)
        checkTextSize();

        //Some essential variables to access views
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        leftButton.setText(new StringBuilder().append(lower).append("...").append(middle));
        rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));

        TextView scoreNum = findViewById(R.id.scoreNum);
        TextView answerNum = findViewById(R.id.answerNum);
        Button startButton = findViewById(R.id.startButton);
        Button changeButton = findViewById(R.id.changeRange);

        TextView finish = findViewById(R.id.finishText);

        //Tracking the right button
        rightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                checkTextSize();

                if ((upper - lower) == 1) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                    middle = -1;
                    if (answer == upper) {
                        score += 10;
                        finalCondition(true);
                    } else {
                        score -= 10;
                        finalCondition(false);
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
                        answerNum.setVisibility(View.VISIBLE);
                        answerNum.setText(R.string.correctAnswer);
                        answerNum.setTextColor(getResources().getColor(R.color.blue));
                    } else {
                        score -= 10;
                        finalCondition(false);
                    }
                } else if (answer >= middle) {
                    score += 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    changeShownText(true);
                } else if (answer < middle) {
                    score -= 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    changeShownText(false);
                }

                if (lower == middle) {
                    leftButton.setText(new StringBuilder().append(lower));
                }
                if (middle == upper) {
                    rightButton.setText(new StringBuilder().append(upper));
                }
            }
        });

        //Tracking the left button
        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                checkTextSize();

                if ((upper - lower) == 1) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(upper));
                    middle = -1;
                    if (answer == lower) {
                        score += 10;
                        finalCondition(true);
                    } else {
                        score -= 10;
                        finalCondition(false);
                    }
                } else if ((upper - lower) == 2) {
                    leftButton.setText(new StringBuilder().append(lower));
                    rightButton.setText(new StringBuilder().append(middle).append("...").append(upper));
                    if (answer == lower && middle != answer) {
                        score += 10;
                        finalCondition(true);
                    } else {
                        score -= 10;
                        finalCondition(false);
                    }
                } else if (answer >= middle) {
                    score -= 10;
                    lower = middle;
                    middle = (int) (upper + lower) / 2;
                    changeShownText(false);
                } else if (answer < middle) {
                    score += 10;
                    upper = middle;
                    middle = (int) (upper + lower) / 2;
                    changeShownText(true);
                }

                if (lower == middle || (upper - lower) == 2) {
                    leftButton.setText(new StringBuilder().append(lower));
                }
                if (middle == upper) {
                    rightButton.setText(new StringBuilder().append(upper));
                }

            }
        });

    }

    //Returning back to initial view
    public void changeRange(View view) {

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
