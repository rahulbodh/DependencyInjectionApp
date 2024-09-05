package com.rahul.mvvmexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionListResponseSchema {

    @SerializedName("items")
    private List<Question> mQuestions;

    public QuestionListResponseSchema(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
    }

    public List<Question> getmQuestions() {
        return mQuestions;
    }
}
