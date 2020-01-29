package com.example.sahil.assign2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.sahil.assign2.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.sahil.assign2.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    /* renamed from: com.bignerdranch.android.geoquiz.QuizActivity$1 */
    class C01951 implements View.OnClickListener {
        C01951() {
        }

        public void onClick(View v) {
            if (CheatActivity.this.mAnswerIsTrue) {
                CheatActivity.this.mAnswerTextView.setText(R.string.true_button);
            } else {
                CheatActivity.this.mAnswerTextView.setText(R.string.false_button);
            }
            CheatActivity.this.setAnswerShownResult(true);
        }
    }

    /* renamed from: com.bignerdranch.android.geoquiz.QuizActivity$2 */
    class C01962 implements View.OnClickListener {
        C01962() {
        }

        public void onClick(View view) {
            CheatActivity.this.finish();
        }
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, QuizActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_cheat);
        this.mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        this.mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        this.mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        this.mShowAnswerButton.setOnClickListener(new C01951());
        ((Button) findViewById(R.id.exit_cheat_button)).setOnClickListener(new C01962());
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(-1, data);
    }
}
