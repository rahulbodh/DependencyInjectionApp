package com.rahul.mvvmexample.network;

import com.rahul.mvvmexample.model.QuestionListResponseSchema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackOverFlowApI {

    @GET("/2.3/questions?order=asc&sort=activity&site=stackoverflow")
    Call<QuestionListResponseSchema> lastActiveQuestion(@Query("page") int page);
}
