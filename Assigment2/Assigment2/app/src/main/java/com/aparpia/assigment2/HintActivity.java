package com.aparpia.assigment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String EXTRA_HINT_IS_TRUE =
            "com.bignerdranch.android.geoquiz.hint";
    private static final String EXTRA_HINT_SHOWN =
            "com.bignerdranch.android.geoquiz.hint_shown";

    private boolean mAnswerIsTrue;
    private Button mExit;
    private TextView mHintTextView;
    private Button mShowHintButton;
    private int showHint = 0;

    public static Intent newIntent(Context packageContext, int hint) {
        Intent intent = new Intent(packageContext, HintActivity.class);
        intent.putExtra(EXTRA_HINT_IS_TRUE, hint);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_HINT_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        showHint = getIntent().getIntExtra(EXTRA_HINT_IS_TRUE, 0);
        mHintTextView = findViewById(R.id.hint_text_view);
        mShowHintButton = findViewById(R.id.show_hint_button);
        mShowHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHintTextView.setText(showHint);
                setAnswerShownResult(true);
            }
        });
        mExit = findViewById(R.id.exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setAnswerShownResult(boolean isHintShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_HINT_SHOWN, isHintShown);
        setResult(RESULT_OK, data);
    }

    public static boolean hintShown(Intent result) {
        return result.getBooleanExtra(EXTRA_HINT_SHOWN, false);
    }
}