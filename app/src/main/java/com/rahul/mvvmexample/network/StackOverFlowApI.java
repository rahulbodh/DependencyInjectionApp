package com.rahul.mvvmexample.network;

import com.rahul.mvvmexample.model.QuestionListResponseSchema;
import com.rahul.mvvmexample.model.SingleQuestionResponseSchema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackOverFlowApI {

    @GET("/2.3/questions?order=asc&sort=activity&site=stackoverflow")
    Call<QuestionListResponseSchema> lastActiveQuestion(@Query("page") int page);


    @GET("/questions/{questionId}?site=stackoverflow&filter=withbody")
    Call<SingleQuestionResponseSchema> questionDetails(@Path("questionId") String questionId);

}
