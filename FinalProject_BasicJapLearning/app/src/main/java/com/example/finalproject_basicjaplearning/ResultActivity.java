package com.example.finalproject_basicjaplearning;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult, tvCorrect, tvWrong, tvQuizCount, tvTimer;
    private MaterialButton btnBack;
    private LinearLayout resultContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResult = findViewById(R.id.tvResult);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvQuizCount = findViewById(R.id.tvQuizCount);
        btnBack = findViewById(R.id.btnBack);
        resultContainer = findViewById(R.id.resultContainer); // Khai báo trong layout XML
        tvTimer = findViewById(R.id.tvTimer);

        int correct = getIntent().getIntExtra("correctAnswers", 0);
        int total = getIntent().getIntExtra("totalQuestions", 0);
        int wrong = total - correct;
        long timer = getIntent().getLongExtra("durationMillis",0);
        int seconds = (int) (timer / 1000) % 60;
        int minutes = (int) (timer / 1000) / 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvCorrect.setText("" + correct);
        tvWrong.setText("" + wrong);
        tvQuizCount.setText(total + " Câu");
        tvResult.setText("Accuracy: " + ((float) correct / total * 100) + "%");
        tvTimer.setText("" + timeFormatted);
        // Lấy danh sách truyền từ QuizActivity
        ArrayList<String> questions = getIntent().getStringArrayListExtra("questions");
        ArrayList<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");
        ArrayList<String> correctAnswers = getIntent().getStringArrayListExtra("correctAnswersList");

        // Hiển thị từng câu hỏi và đáp án
        for (int i = 0; i < questions.size(); i++) {
            String q = questions.get(i);
            String user = userAnswers.get(i);
            String correctAns = correctAnswers.get(i);

            TextView tv = new TextView(this);
            tv.setText((i + 1) + ". " + q + "\nSelected: " + user + "\nCorrect: " + correctAns);
            tv.setPadding(10, 20, 10, 20);
            tv.setTextSize(16);

            if (user.equals(correctAns)) {
                tv.setTextColor(Color.parseColor("#2E7D32")); // Màu xanh (đúng)
            } else {
                tv.setTextColor(Color.parseColor("#C62828")); // Màu đỏ (sai)
            }

            resultContainer.addView(tv);
        }

        btnBack.setOnClickListener(view -> finish());
    }
}
