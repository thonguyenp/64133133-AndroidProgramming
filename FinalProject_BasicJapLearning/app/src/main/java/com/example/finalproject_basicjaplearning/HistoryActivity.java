package com.example.finalproject_basicjaplearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;

public class HistoryActivity extends AppCompatActivity {
    private TextView tvHistory;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvHistory = findViewById(R.id.tvHistory);
        prefsHelper = new SharedPreferencesHelper(this);

        Map<String, KanaStats> kanaStats = prefsHelper.getAllKanaStats();
        StringBuilder sb = new StringBuilder();

        if (kanaStats.isEmpty()) {
            sb.append("Chưa có dữ liệu lịch sử.");
        } else {
            sb.append("Tỉ lệ đúng từng chữ Kana:\n");
            for (Map.Entry<String, KanaStats> entry : kanaStats.entrySet()) {
                KanaStats stats = entry.getValue();
                sb.append(entry.getKey()).append(": ").append(String.format("%.2f", stats.getCorrectRate())).append("%\n");
            }
        }

        tvHistory.setText(sb.toString());
    }
}

