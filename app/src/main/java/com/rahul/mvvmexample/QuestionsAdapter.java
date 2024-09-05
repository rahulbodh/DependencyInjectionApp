package com.rahul.mvvmexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.mvvmexample.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private final OnQuestionClickListener onQuestionClickListener;
    private List<Question> questionList = new ArrayList<>(0);

    public QuestionsAdapter(OnQuestionClickListener onQuestionClickListener) {
        this.onQuestionClickListener = onQuestionClickListener;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Properly inflate the view without attaching to the parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quetion_list_item, parent, false);
        return new QuestionsViewHolder(view);
    }

    public void bindData(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        // Get the question for the current position
        Question question = questionList.get(position);

        // Set the title text
        holder.txt_title.setText(question.getTitle());

        // Handle click events
        holder.txt_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuestionClickListener.onQuestionClick(question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_title;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }

    // Interface for handling click events
    public interface OnQuestionClickListener {
        void onQuestionClick(Question question);
    }
}
