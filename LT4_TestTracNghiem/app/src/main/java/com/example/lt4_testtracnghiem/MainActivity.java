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
        TimView();
        questions = QuestionBank.getQuestions();
        showQuestion();
    }

    void TimView ()
    {
        tvRomaji = findViewById(R.id.tvRomaji);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvScore = findViewById(R.id.tvScore);
        gridOptions = findViewById(R.id.gridOptions);
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size())
        {
            //Khi hoàn thành bài trắc nghiệm
            tvRomaji.setText("Hoàn thành!");
            tvScore.setText("Điểm số: " + score + "/10");
            tvScore.setVisibility(View.VISIBLE);
            gridOptions.setVisibility(View.GONE);
            return;
        }
        //Lấy ra romanji và vị trí của câu hỏi hiện tại
        Question currentQuestion = questions.get(currentQuestionIndex);
        tvRomaji.setText(currentQuestion.getRomanji());
        tvQuestionNumber.setText("Câu: " + (currentQuestionIndex + 1) + "/10");
        //Xóa hết các button trong gridView
        gridOptions.removeAllViews();
        //Duyệt hết các phần tử trong List options
        for (String option : currentQuestion.getOptions())
        {
            //Tạo btn mới -> đưa list option (optlist ở class QuestionBank) vào btn -> kích hoạt hàm checkAnswer khi bấm vào đáp án
            //Cuối cùng thì thêm nút vào gridLayout
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
