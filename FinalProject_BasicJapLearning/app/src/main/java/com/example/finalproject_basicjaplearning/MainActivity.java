package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnFullTest, btnRandomTest, btnHistory, btnRomajiTest;

    CardView btnKanjiLookup, btnQuiz;


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
        btnKanjiLookup = findViewById(R.id.btnKanjiLookup);
        btnQuiz = findViewById(R.id.btnQuiz);

        btnKanjiLookup.setOnClickListener(v -> startActivity(new Intent(this, KanjiActivity.class)));
        btnQuiz.setOnClickListener(v -> startActivity(new Intent(this, QuizMenuActivity.class)));
    }
}