package com.aparpia.assigment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final int REQUEST_CODE_HINT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private Button mHintButton;
    private TextView mQuestionTextView;
    private TextView mMarkTextView;
    private TextView mAnswerTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, R.string.hint_australia, true),
            new Question(R.string.question_oceans, R.string.hint_oceans, true),
            new Question(R.string.question_mideast, R.string.hint_mideast, false),
            new Question(R.string.question_africa, R.string.hint_africa, false),
            new Question(R.string.question_americas, R.string.hint_americas, true),
            new Question(R.string.question_asia, R.string.hint_asia, true),

    };

    private int mCurrentIndex = 0;
    private boolean mCheater;
    private boolean mHintShown;
    private int score = 0;
    private int mQuestionsAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mMarkTextView = (TextView) findViewById(R.id.mark_text_view);
        mMarkTextView.setText(String.valueOf(score));
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mAnswerTextView.setText("0");


        mTrueButton = findViewById(R.id.True);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.False);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mPrevButton = findViewById(R.id.Prev);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionBank.length;
                }
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                mCheater = false;
                updateQuestion();
            }
        });

        mNextButton = findViewById(R.id.Next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = findViewById(R.id.Cheat);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion();
        mHintButton = findViewById(R.id.Hint);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hintId = mQuestionBank[mCurrentIndex].getHintId();
                Intent intent = HintActivity.newIntent(MainActivity.this, hintId);
                startActivityForResult(intent, REQUEST_CODE_HINT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mCheater = CheatActivity.wasAnswerShown(data);
        }
        if (requestCode == REQUEST_CODE_HINT) {
            if (data == null) {
                return;
            }
            mHintShown = HintActivity.hintShown(data);
            if (mHintShown == true) {
                mQuestionBank[mCurrentIndex].setUserHinted(true);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        if (!mQuestionBank[mCurrentIndex].getAnswered()) {
            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
            mHintShown = mQuestionBank[mCurrentIndex].didUserHint();
            int messageResId = 0;
            if (mCheater) {
                messageResId = R.string.judgment_toast;
            } else {
                if (userPressedTrue == answerIsTrue && mHintShown == true) {
                    messageResId = R.string.correct_toast_1;
                    score++;
                } else if (userPressedTrue == answerIsTrue && mHintShown == false) {
                    messageResId = R.string.correct_toast_2;
                    score += 2;
                } else if (userPressedTrue != answerIsTrue && mHintShown == true) {
                    messageResId = R.string.incorrect_toast_1;
                    score -= 2;
                } else if (userPressedTrue != answerIsTrue && mHintShown == false) {
                    messageResId = R.string.incorrect_toast_2;
                    score--;
                }
            }
            mQuestionsAnswered++;
            mAnswerTextView.setText(String.valueOf(mQuestionsAnswered));
            mMarkTextView.setText(String.valueOf(score));
            mQuestionBank[mCurrentIndex].setAnswered(true);
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Question Completed!", Toast.LENGTH_SHORT).show();
        }
    }
}
