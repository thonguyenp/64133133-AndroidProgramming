package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubKanjiActivity extends AppCompatActivity {

    private TextView tvKanji, tvhanViet, tvMeaning, tvOnReading, tvKunReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_kanji);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvKanji = findViewById(R.id.tvKanji);
        tvhanViet = findViewById(R.id.tvhanViet);
        tvMeaning = findViewById(R.id.tvMeaning);
        tvOnReading = findViewById(R.id.tvOnReading);
        tvKunReading = findViewById(R.id.tvKunReading);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        tvKanji.setText(intent.getStringExtra("kanji"));
        tvhanViet.setText("Nghĩa Hán: " + intent.getStringExtra("hanViet"));
        tvMeaning.setText("Nghĩa: " + intent.getStringExtra("meaning"));
        tvOnReading.setText("Âm On: " + intent.getStringExtra("onReading"));
        tvKunReading.setText("Âm Kun: " + intent.getStringExtra("kunReading"));

    }
}