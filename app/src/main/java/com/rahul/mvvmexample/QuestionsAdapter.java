package com.rahul.mvvmexample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private final OnQuestionClickListener onQuetionClickListener;
    private List<Question> questionList = new ArrayList<>(0);

    public QuestionsAdapter(OnQuestionClickListener onQuestionClickListener, OnQuestionClickListener onQuetionClickListener) {
        this.onQuetionClickListener = onQuetionClickListener;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.quetion_list_item, parent);
        return new QuestionsViewHolder(view);
    }


    public void bindData(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        holder.txt_title.setText(questionList.get(position).getTitle());
        holder.txt_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuetionClickListener.onQuestionClick(questionList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_title;
        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}
