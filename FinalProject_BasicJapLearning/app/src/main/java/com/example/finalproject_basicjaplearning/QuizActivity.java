package com.example.finalproject_basicjaplearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvTimer;

    private Button[] optionButtons = new Button[4];
    private List<KanaBank> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private boolean isRandom;
    private boolean isRomajiQuestion;
    private SharedPreferencesHelper prefsHelper;
    private Random random = new Random();

    private long startTime;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> userAnswerList = new ArrayList<>();
    private ArrayList<String> correctAnswerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        prefsHelper = new SharedPreferencesHelper(this);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);

        optionButtons[0] = findViewById(R.id.btnOption1);
        optionButtons[1] = findViewById(R.id.btnOption2);
        optionButtons[2] = findViewById(R.id.btnOption3);
        optionButtons[3] = findViewById(R.id.btnOption4);

        isRandom = getIntent().getBooleanExtra("isRandom", false);
        isRomajiQuestion = getIntent().getBooleanExtra("isRomajiQuestion", false);

        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedMillis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
                timerHandler.postDelayed(this, 1000); // Cập nhật mỗi giây
            }
        };
        timerHandler.post(timerRunnable);

        setupQuestions();
        startTime = System.currentTimeMillis(); // ⭐ Bắt đầu tính giờ
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

        String questionText;
        String correctAnswer;
        List<String> options = new ArrayList<>();

        if (isRomajiQuestion) {
            questionText = currentKana.romaji;
            correctAnswer = currentKana.hiragana;
            options.add(correctAnswer);
            while (options.size() < 4) {
                String randomKana = KanaBank.values()[random.nextInt(KanaBank.values().length)].hiragana;
                if (!options.contains(randomKana)) {
                    options.add(randomKana);
                }
            }
        } else {
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
        String question = isRomajiQuestion ? currentKana.romaji : currentKana.hiragana;
        String correct = isRomajiQuestion ? currentKana.hiragana : currentKana.romaji;

        // Lưu câu hỏi và đáp án
        questionList.add(question);
        userAnswerList.add(selected);
        correctAnswerList.add(correct);

        // Kiểm tra
        isCorrect = selected.equals(correct);
        if (isCorrect) {
            correctAnswers++;
        }

        prefsHelper.updateKanaStat(currentKana.hiragana, isCorrect);

        currentQuestionIndex++;
        showNextQuestion();
    }

    private void finishQuiz() {
        timerHandler.removeCallbacks(timerRunnable);
        long endTime = System.currentTimeMillis(); // Kết thúc tính giờ
        long durationMillis = endTime - startTime;

//        int totalQuestions = prefsHelper.getTotalQuestions() + questions.size();
//        int totalCorrect = prefsHelper.getCorrectAnswers() + correctAnswers;
//        prefsHelper.saveResult(totalQuestions, totalCorrect);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        intent.putExtra("totalQuestions", questions.size());
        intent.putExtra("durationMillis", durationMillis); // Truyền thời gian
        intent.putStringArrayListExtra("questions", questionList);
        intent.putStringArrayListExtra("userAnswers", userAnswerList);
        intent.putStringArrayListExtra("correctAnswersList", correctAnswerList);
        startActivity(intent);
//        finish();
    }
}
