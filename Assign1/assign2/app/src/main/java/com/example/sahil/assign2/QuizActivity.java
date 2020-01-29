package com.example.sahil.assign2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final int REQUEST_CODE_HINT = 1;
    private static final String TAG = "QuizActivity";
    private Button mCheatButton;
    private int mCurrentIndex = 0;
    private Button mFalseButton;
    private Button mHintButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Question[] mQuestionBank = new Question[]{new Question(R.string.question_australia, true, false, false, false, R.string.hint_australia), new Question(R.string.question_oceans, true, false, false, false, R.string.hint_oceans), new Question(R.string.question_mideast, false, false, false, false, R.string.hint_mideast), new Question(R.string.question_africa, false, false, false, false, R.string.hint_africa), new Question(R.string.question_americas, true, false, false, false, R.string.hint_americas), new Question(R.string.question_asia, true, false, false, false, R.string.hint_asia)};
    private TextView mQuestionTextView;
    private Button mTrueButton;
    int totalMarks = 0;


    class QuizActivity1 implements OnClickListener {
        QuizActivity1() {
        }

        public void onClick(View v) {
            QuizActivity.this.checkAnswer(true);
        }
    }


    class QuizActivity2 implements OnClickListener {
        QuizActivity2() {
        }

        public void onClick(View v) {
            QuizActivity.this.checkAnswer(false);
        }
    }


    class QuizActivity3 implements OnClickListener {
        QuizActivity3() {
        }

        public void onClick(View v) {
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.mCurrentIndex = (quizActivity.mCurrentIndex + 1) % QuizActivity.this.mQuestionBank.length;
            QuizActivity.this.updateQuestion();
        }
    }


    class QuizActivity4 implements OnClickListener {
        QuizActivity4() {
        }

        public void onClick(View v) {
            QuizActivity quizActivity = QuizActivity.this;
            quizActivity.mCurrentIndex = quizActivity.mCurrentIndex - 1;
            if (QuizActivity.this.mCurrentIndex == -1) {
                quizActivity = QuizActivity.this;
                quizActivity.mCurrentIndex = quizActivity.mQuestionBank.length - 1;
            }
            QuizActivity.this.updateQuestion();
        }
    }


    class QuizActivity5 implements OnClickListener {
        QuizActivity5() {
        }

        public void onClick(View v) {
            //QuizActivity.this.startActivityForResult(CheatActivity.newIntent(QuizActivity.this, QuizActivity.this.mQuestionBank[QuizActivity.this.mCurrentIndex].isAnswerTrue()), 0);
            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
            Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
            startActivityForResult(intent, REQUEST_CODE_CHEAT);

        }
    }

    class QuizActivity6 implements OnClickListener {
        QuizActivity6() {
        }

        public void onClick(View v) {
           // QuizActivity.this.startActivityForResult(HintActivity.newIntent(QuizActivity.this, QuizActivity.this.mQuestionBank[QuizActivity.this.mCurrentIndex].getTextHintId()),1);
            int hintId = mQuestionBank[mCurrentIndex].getTextHintId();
            Intent intent = HintActivity.newIntent(QuizActivity.this, hintId);
            startActivityForResult(intent, REQUEST_CODE_HINT);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView( R.layout.activity_quiz);
        if (savedInstanceState != null) {
            this.mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        this.mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        this.mTrueButton = (Button) findViewById(R.id.true_button);
        this.mTrueButton.setOnClickListener(new QuizActivity1());
        this.mFalseButton = (Button) findViewById(R.id.false_button);
        this.mFalseButton.setOnClickListener(new QuizActivity2());
        this.mNextButton = (Button) findViewById(R.id.next_button);
        this.mNextButton.setOnClickListener(new QuizActivity3());
        this.mPrevButton = (Button) findViewById(R.id.previous_button);
        this.mPrevButton.setOnClickListener(new QuizActivity4());
        this.mCheatButton = (Button) findViewById(R.id.cheat_button);
        this.mCheatButton.setOnClickListener(new QuizActivity5());
        this.mHintButton = (Button) findViewById(R.id.hint_button);
        this.mHintButton.setOnClickListener(new QuizActivity6());
        updateQuestion();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            if (requestCode == 0) {
                if (data != null) {
                    this.mQuestionBank[this.mCurrentIndex].setUserCheated(CheatActivity.wasAnswerShown(data));
                } else {
                    return;
                }
            }
            if (requestCode == 1) {
                if (data != null) {
                    this.mQuestionBank[this.mCurrentIndex].setHintGiven(HintActivity.wasHintShown(data));
                }
            }
        }
    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, this.mCurrentIndex);
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        this.mQuestionTextView.setText(this.mQuestionBank[this.mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = this.mQuestionBank[this.mCurrentIndex].isAnswerTrue();
        if (this.mQuestionBank[this.mCurrentIndex].isComplete()) {
            Toast.makeText(this, "Question Completed!", Toast.LENGTH_SHORT).show();
        } else if (this.mQuestionBank[this.mCurrentIndex].didUserCheat()) {
            Toast.makeText(this, "Cheating is Wrong!", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder stringBuilder;
            this.mQuestionBank[this.mCurrentIndex].setCompleted(true);
            TextView textView = (TextView) findViewById(R.id.questionsID);
            int temp = Integer.parseInt(textView.getText().toString()) + 1;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(BuildConfig.FLAVOR);
            stringBuilder2.append(temp);
            textView.setText(stringBuilder2.toString());
            if (userPressedTrue == answerIsTrue) {
                if (this.mQuestionBank[this.mCurrentIndex].wasHintGiven()) {
                    this.totalMarks++;
                    Toast.makeText(this, "+1 Marks", Toast.LENGTH_SHORT).show();
                    textView = (TextView) findViewById(R.id.marksID);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(BuildConfig.FLAVOR);
                    stringBuilder.append(this.totalMarks);
                    textView.setText(stringBuilder.toString());
                }
            }
            if (userPressedTrue == answerIsTrue) {
                if (!this.mQuestionBank[this.mCurrentIndex].wasHintGiven()) {
                    this.totalMarks += 2;
                    Toast.makeText(this, "+2 Marks", Toast.LENGTH_SHORT).show();
                    textView = (TextView) findViewById(R.id.marksID);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(BuildConfig.FLAVOR);
                    stringBuilder.append(this.totalMarks);
                    textView.setText(stringBuilder.toString());
                }
            }
            if (userPressedTrue != answerIsTrue) {
                if (this.mQuestionBank[this.mCurrentIndex].wasHintGiven()) {
                    this.totalMarks -= 2;
                    Toast.makeText(this, "-2 Marks", Toast.LENGTH_SHORT).show();
                    textView = (TextView) findViewById(R.id.marksID);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(BuildConfig.FLAVOR);
                    stringBuilder.append(this.totalMarks);
                    textView.setText(stringBuilder.toString());
                }
            }
            if (userPressedTrue != answerIsTrue) {
                if (!this.mQuestionBank[this.mCurrentIndex].wasHintGiven()) {
                    this.totalMarks--;
                    Toast.makeText(this, "-1 Marks",Toast.LENGTH_SHORT ).show();               }
            }
            textView = (TextView) findViewById(R.id.marksID);
            stringBuilder = new StringBuilder();
            stringBuilder.append(BuildConfig.FLAVOR);
            stringBuilder.append(this.totalMarks);
            textView.setText(stringBuilder.toString());
        }
    }
}