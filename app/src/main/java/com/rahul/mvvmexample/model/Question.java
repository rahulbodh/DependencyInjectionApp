package com.rahul.mvvmexample.model;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("title")
    private String title;

    @SerializedName("question_id")
    private Integer questionId;

    public Question(String title, Integer questionId) {
        this.title = title;
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}


