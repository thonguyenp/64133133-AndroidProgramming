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
    private boolean isRomajiQuestion;
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
        isRomajiQuestion = getIntent().getBooleanExtra("isRomajiQuestion", false);

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

        // Thiết lập câu hỏi
        String questionText;
        String correctAnswer;
        List<String> options = new ArrayList<>();

        if (isRomajiQuestion) {
            questionText = currentKana.romaji;
            correctAnswer = currentKana.hiragana; // hoặc currentKana.katakana nếu muốn dùng katakana
            options.add(correctAnswer);
            while (options.size() < 4) {
                String randomKana = KanaBank.values()[random.nextInt(KanaBank.values().length)].hiragana;
                if (!options.contains(randomKana)) {
                    options.add(randomKana);
                }
            }
        } else {
            //ngược lại, câu hỏi là hiragana và câu trả lời là romanji
            questionText = currentKana.hiragana;
            correctAnswer = currentKana.romaji;
            options.add(correctAnswer);
            while (options.size() < 4) {
                String randomRomaji = KanaBank.values()[random.nextInt(KanaBank.values().length)].romaji;
                if (!options.contains(randomRomaji)) {
                    options.add(randomRomaji);
                }
            }
        }

        tvQuestion.setText(questionText);
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            String option = options.get(i);
            optionButtons[i].setText(option);
            optionButtons[i].setOnClickListener(v -> checkAnswer(option, currentKana));
        }
    }

    private void checkAnswer(String selected, KanaBank currentKana) {
        boolean isCorrect;

        if (isRomajiQuestion) {
            // Câu hỏi là romaji, đáp án là hiragana
            isCorrect = selected.equals(currentKana.hiragana);
        } else {
            // Câu hỏi là hiragana, đáp án là romaji
            isCorrect = selected.equals(currentKana.romaji);
        }

        if (isCorrect) {
            correctAnswers++;
        }

        // Cập nhật tỉ lệ đúng cho kana này
        prefsHelper.updateKanaStat(currentKana.hiragana, isCorrect);

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
