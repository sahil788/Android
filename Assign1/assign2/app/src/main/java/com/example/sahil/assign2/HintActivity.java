package com.example.sahil.assign2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

    public class HintActivity extends AppCompatActivity {
        private static final String EXTRA_ANSWER_IS_TRUE = "com.example.sahil.assign2.answer_is_true";
        private static final String EXTRA_ANSWER_SHOWN = "com.example.sahil.assign2.answer_shown";
        private int mAnswerIsTrue;
        private TextView mHintTextView;
        private Button mShowHintButton;

        /* renamed from: com.bignerdranch.android.geoquiz.HintActivity$1 */
        class C01971 implements OnClickListener {
            C01971() {
            }

            public void onClick(View v) {
                HintActivity.this.mHintTextView.setText(HintActivity.this.mAnswerIsTrue);
                HintActivity.this.setAnswerShownResult(true);
            }
        }

        /* renamed from: com.bignerdranch.android.geoquiz.HintActivity$2 */
        class C01982 implements OnClickListener {
            C01982() {
            }

            public void onClick(View view) {
                HintActivity.this.finish();
            }
        }

        public static Intent newIntent(Context packageContext, int answerIsTrue) {
            Intent intent = new Intent(packageContext, HintActivity.class);
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
            return intent;
        }

        public static boolean wasHintShown(Intent result) {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView((int) R.layout.activity_hint);
            this.mAnswerIsTrue = getIntent().getIntExtra(EXTRA_ANSWER_IS_TRUE, 0);
            this.mHintTextView = (TextView) findViewById(R.id.hint_text_view);
            this.mShowHintButton = (Button) findViewById(R.id.show_hint_button);
            this.mShowHintButton.setOnClickListener(new C01971());
            ((Button) findViewById(R.id.exit_hint_button)).setOnClickListener(new C01982());
        }

        private void setAnswerShownResult(boolean isAnswerShown) {
            Intent data = new Intent();
            data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
            setResult(-1, data);
        }
}
