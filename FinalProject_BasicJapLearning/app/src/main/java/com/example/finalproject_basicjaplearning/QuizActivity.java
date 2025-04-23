package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private Button[] optionButtons = new Button[4];
    private List<KanaBank> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private boolean isRandom;
    private SharedPreferencesHelper prefsHelper;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        prefsHelper = new SharedPreferencesHelper(this);
        tvQuestion = findViewById(R.id.tvQuestion);
        optionButtons[0] = findViewById(R.id.btnOption1);
        optionButtons[1] = findViewById(R.id.btnOption2);
        optionButtons[2] = findViewById(R.id.btnOption3);
        optionButtons[3] = findViewById(R.id.btnOption4);

        isRandom = getIntent().getBooleanExtra("isRandom", false);

        setupQuestions();
        showNextQuestion();
    }

    private void setupQuestions() {
        questions = new ArrayList<>();
        Collections.addAll(questions, KanaBank.values());
        if (isRandom) {
            Collections.shuffle(questions);
            if (questions.size() > 10) {
                questions = questions.subList(0, 10);
            }
        }
    }

    private void showNextQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        KanaBank currentKana = questions.get(currentQuestionIndex);
        tvQuestion.setText(currentKana.hiragana);

        List<String> options = new ArrayList<>();
        options.add(currentKana.romaji);

        while (options.size() < 4) {
            String randomOption = KanaBank.values()[random.nextInt(KanaBank.values().length)].romaji;
            if (!options.contains(randomOption)) {
                options.add(randomOption);
            }
        }

        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options.get(i));
            optionButtons[i].setOnClickListener(v -> checkAnswer(((Button) v).getText().toString(), currentKana.romaji));
        }
    }

    private void checkAnswer(String selected, String correct) {
        if (selected.equals(correct)) {
            correctAnswers++;
        }
        currentQuestionIndex++;
        showNextQuestion();
    }

    private void finishQuiz() {
        int totalQuestions = prefsHelper.getTotalQuestions() + questions.size();
        int totalCorrect = prefsHelper.getCorrectAnswers() + correctAnswers;
        prefsHelper.saveResult(totalQuestions, totalCorrect);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        intent.putExtra("totalQuestions", questions.size());
        startActivity(intent);
        finish();
    }
}