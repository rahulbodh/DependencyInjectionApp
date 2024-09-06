package com.rahul.mvvmexample;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.rahul.mvvmexample.databinding.ActivityQuestionDetailBinding;
import com.rahul.mvvmexample.model.SingleQuestionResponseSchema;
import com.rahul.mvvmexample.network.RetrofitInstance;
import com.rahul.mvvmexample.network.StackOverFlowApI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailActivity extends AppCompatActivity implements Callback<SingleQuestionResponseSchema> {

    private ActivityQuestionDetailBinding binding;

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private TextView mQuestionBody;
    private StackOverFlowApI mStackOverFlowApI;
    private String mQuestionId;
    private Call<SingleQuestionResponseSchema> mcall; // <1>

    public static void start(Context context, String questionId){
        Intent i = new Intent(context, QuestionDetailActivity.class);
        i.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuestionDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mStackOverFlowApI = RetrofitInstance.getStackoverflowApi();
        mQuestionId = getIntent().getStringExtra(EXTRA_QUESTION_ID);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");

                // Using View Binding to access the TextView
                i.putExtra(Intent.EXTRA_TEXT, binding.textScrollingView.text2.getText().toString());
                startActivity(i);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        mcall = mStackOverFlowApI.questionDetails(mQuestionId);
        mcall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mcall != null){
            mcall.cancel();
        }
    }

    @Override
    public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
        SingleQuestionResponseSchema questionResponseSchema;
        if (response.isSuccessful() && (questionResponseSchema = response.body()) != null) {

            String questionBody = questionResponseSchema.getQuestions().getmBody();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                binding.textScrollingView.text2.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
            } else {
                binding.textScrollingView.text2.setText(Html.fromHtml(questionBody));
            }
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable throwable) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();

    }


}