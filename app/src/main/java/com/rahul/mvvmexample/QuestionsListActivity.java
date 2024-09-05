package com.rahul.mvvmexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.mvvmexample.model.Question;
import com.rahul.mvvmexample.model.QuestionListResponseSchema;
import com.rahul.mvvmexample.network.StackOverFlowApI;
import com.rahul.mvvmexample.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements Callback<QuestionListResponseSchema> {

    private RecyclerView recyclerView;
    private QuestionsAdapter questionsAdapter;
    private StackOverFlowApI stackOverFlowApI;

    Call<QuestionListResponseSchema> mcall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        recyclerView = findViewById(R.id.recyclerView);
        questionsAdapter = new QuestionsAdapter(new QuestionsAdapter.OnQuestionClickListener() {
            @Override
            public void onQuestionClick(Question question) {

            }
        });
//       questionsAdapter = new QuestionsAdapter(new OnQuestionClickListener() {
//            @Override
//            public void onQuestionClick(Question question) {
//                FragmentManager fm = getSupportFragmentManager();
//                // QuestionDetailsActivity.start(QuestionsListActivity.this, question.getQuestionId(), fm);
//            }
//        });
        recyclerView.setAdapter(questionsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // INIT RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackOverFlowApI = retrofit.create(StackOverFlowApI.class);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mcall = stackOverFlowApI.lastActiveQuestion(10);
        mcall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mcall != null) {
            mcall.cancel();
        }

    }

    @Override
    public void onResponse(Call<QuestionListResponseSchema> call, Response<QuestionListResponseSchema> response) {

        QuestionListResponseSchema responseSchema;
        if (response.isSuccessful() && response.body() != null) {

            responseSchema = response.body();
            questionsAdapter.bindData(responseSchema.getmQuestions());

        }else{
            onFailure(call, null);
        }

    }

    @Override
    public void onFailure(Call<QuestionListResponseSchema> call, Throwable throwable) {

        FragmentManager  fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();

    }
}