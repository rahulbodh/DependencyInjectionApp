package com.rahul.mvvmexample.network;

import com.rahul.mvvmexample.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static StackOverFlowApI stackoverflowApi;

    public static StackOverFlowApI getStackoverflowApi() {
        if (stackoverflowApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            stackoverflowApi = retrofit.create(StackOverFlowApI.class);
        }
        return stackoverflowApi;
    }
}