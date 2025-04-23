package com.example.finalproject_basicjaplearning;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String PREFS_NAME = "nihongo_prefs";
    private static final String KEY_TOTAL_QUESTIONS = "total_questions";
    private static final String KEY_CORRECT_ANSWERS = "correct_answers";

    private SharedPreferences prefs;

    public SharedPreferencesHelper(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveResult(int totalQuestions, int correctAnswers) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TOTAL_QUESTIONS, totalQuestions);
        editor.putInt(KEY_CORRECT_ANSWERS, correctAnswers);
        editor.apply();
    }

    public int getTotalQuestions() {
        return prefs.getInt(KEY_TOTAL_QUESTIONS, 0);
    }

    public int getCorrectAnswers() {
        return prefs.getInt(KEY_CORRECT_ANSWERS, 0);
    }
}
