package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnFullTest, btnRandomTest, btnHistory, btnKanjiLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnFullTest = findViewById(R.id.btnFullTest);
        btnRandomTest = findViewById(R.id.btnRandomTest);
        btnHistory = findViewById(R.id.btnHistory);
        btnKanjiLookup = findViewById(R.id.btnKanjiLookup);


        btnFullTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(false);
            }
        });
        btnRandomTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(true);
            }
        });
        btnHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        btnKanjiLookup.setOnClickListener(v -> startActivity(new Intent(this, KanjiActivity.class)));
    }
    private void startQuiz(boolean isFullTest) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("isRandom", isFullTest);
        startActivity(intent);
    }



}