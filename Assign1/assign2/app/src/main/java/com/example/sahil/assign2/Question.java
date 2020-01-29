package com.example.sahil.assign2;

import android.support.v7.app.AppCompatActivity;

public class Question extends AppCompatActivity {

    private boolean mAnswerTrue;
    private boolean mCompleted;
    private boolean mHintGiven;
    private int mTextHintId;
    private int mTextResId;
    private boolean mUserCheated;

    public Question(int textResId, boolean answerTrue, boolean userCheated, boolean hintGiven, boolean completed, int textHintId) {
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
        this.mUserCheated = userCheated;
        this.mHintGiven = hintGiven;
        this.mCompleted = completed;
        this.mTextHintId = textHintId;
    }

    public int getTextResId() {
        return this.mTextResId;
    }

    public boolean isAnswerTrue() {
        return this.mAnswerTrue;
    }

    public boolean didUserCheat() {
        return this.mUserCheated;
    }

    public void setUserCheated(boolean userCheated) {
        this.mUserCheated = userCheated;
    }

    public boolean wasHintGiven() {
        return this.mHintGiven;
    }

    public void setHintGiven(boolean hintGiven) {
        this.mHintGiven = hintGiven;
    }

    public boolean isComplete() {
        return this.mCompleted;
    }

    public void setCompleted(boolean completed) {
        this.mCompleted = completed;
    }

    public int getTextHintId() {
        return this.mTextHintId;
    }
}
