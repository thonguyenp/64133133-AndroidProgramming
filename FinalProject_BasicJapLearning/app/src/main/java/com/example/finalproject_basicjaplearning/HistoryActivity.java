package com.example.finalproject_basicjaplearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryActivity extends AppCompatActivity {

    private TextView tvHistory;
    private SharedPreferencesHelper prefsHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvHistory = findViewById(R.id.tvHistory);
        prefsHelper = new SharedPreferencesHelper(this);

        int totalQuestions = prefsHelper.getTotalQuestions();
        int correctAnswers = prefsHelper.getCorrectAnswers();

        if (totalQuestions == 0) {
            tvHistory.setText("Chưa có dữ liệu lịch sử.");
        } else {
            int rate = (correctAnswers * 100) / totalQuestions;
            tvHistory.setText("Tổng số câu đã làm: " + totalQuestions + "\nTỉ lệ đúng: " + rate + "%");
        }

    }
}