package com.example.lt4_testtracnghiem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvRomaji, tvQuestionNumber, tvScore;
    private GridLayout gridOptions;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRomaji = findViewById(R.id.tvRomaji);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvScore = findViewById(R.id.tvScore);
        gridOptions = findViewById(R.id.gridOptions);

        questions = QuestionBank.getQuestions();
        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            tvRomaji.setText("Hoàn thành!");
            tvScore.setText("Điểm số: " + score + "/10");
            tvScore.setVisibility(View.VISIBLE);
            gridOptions.setVisibility(View.GONE);
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        tvRomaji.setText(currentQuestion.getRomanji());
        tvQuestionNumber.setText("Câu: " + (currentQuestionIndex + 1) + "/10");

        gridOptions.removeAllViews();

        for (String option : currentQuestion.getOptions()) {
            Button btnOption = new Button(this);
            btnOption.setText(option);
            btnOption.setOnClickListener(view -> checkAnswer(option));
            gridOptions.addView(btnOption);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (selectedAnswer.equals(currentQuestion.getAnswer())) {
            score++;
//            Toast.makeText(this, "Đúng!", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Sai! Đáp án: " + currentQuestion.getAnswer(), Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        showQuestion();
    }
}
