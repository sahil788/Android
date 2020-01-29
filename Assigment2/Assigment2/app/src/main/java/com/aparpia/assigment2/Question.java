package com.aparpia.assigment2;

class Question {

        private int mTextResId;
        private int mTextHintId;
        private boolean mAnswerTrue;
        private boolean mUserCheated = false;
        private boolean mAnswered = false;
        private boolean mUserHinted = false;

        public Question(int textResId, int textHintId, boolean answerTrue) {
            mTextResId = textResId;
            mTextHintId = textHintId;
            mAnswerTrue = answerTrue;

        }

        public int getTextResId() {
            return mTextResId;
        }

        public int getHintId() { return mTextHintId; }

        public void setTextResId(int textResId) {
            mTextResId = textResId;
        }

        public boolean isAnswerTrue() {
            return mAnswerTrue;
        }

        public void setAnswerTrue(boolean answerTrue) {
            mAnswerTrue = answerTrue;
        }

        public boolean getAnswered() { return this.mAnswered; }

        public void setAnswered(boolean isAnswered) { this.mAnswered = isAnswered; }

        public boolean didUserCheat(){ return mUserCheated; }

        public void setUserCheated(boolean userCheated){ mUserCheated = userCheated; }

        public boolean didUserHint(){ return mUserHinted; }

        public void setUserHinted(boolean userHinted){ mUserHinted = userHinted; }
    }