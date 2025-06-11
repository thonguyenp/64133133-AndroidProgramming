package com.example.finalproject_basicjaplearning;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("KanaStats", Context.MODE_PRIVATE);
    }

    public void updateKanaStat(String kana, boolean isCorrect) {
        KanaStats stats = getKanaStats(kana);
        if (isCorrect) {
            stats.incrementCorrect();
        } else {
            stats.incrementIncorrect();
        }
        saveKanaStats(kana, stats);
    }

    //Chuỗi json -> Đối tượng lớp KanaStats
    public KanaStats getKanaStats(String kana) {
        String kanaStatsJson = sharedPreferences.getString(kana, "");
        if (kanaStatsJson.isEmpty()) //nếu chữ kana đó chưa có dữ liệu
        {
            return new KanaStats(); // tạo bộ value gồm correctCount và incorrectCount
        }
        return new Gson().fromJson(kanaStatsJson, KanaStats.class);
    }

    public void saveKanaStats(String kana, KanaStats stats) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String kanaStatsJson = new Gson().toJson(stats);
        editor.putString(kana, kanaStatsJson);
        editor.apply();
    }

    public Map<String, KanaStats> getAllKanaStats() {
        Map<String, ?> allStats = sharedPreferences.getAll();
        Map<String, KanaStats> kanaStatsMap = new HashMap<>();
        for (Map.Entry<String, ?> entry : allStats.entrySet()) {
            if (entry.getValue() instanceof String) {
                //nếu value là string -> đổi value thành đối tượng lớp KanaStats
                KanaStats stats = new Gson().fromJson((String) entry.getValue(), KanaStats.class);
                //Đưa đối tượng vừa tạo vào lại value của chính key trùng với key trong kanaStatsMap
                kanaStatsMap.put(entry.getKey(), stats);
            }
        }
        return kanaStatsMap;
    }

    // Lưu trữ kết quả tổng quan của bài kiểm tra
//    public void saveResult(int totalQuestions, int correctAnswers) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("totalQuestions", totalQuestions);
//        editor.putInt("correctAnswers", correctAnswers);
//        editor.apply();
//    }

    // Lấy tổng số câu hỏi đã làm
    public int getTotalQuestions() {
        return sharedPreferences.getInt("totalQuestions", 0);
    }

    // Lấy số câu trả lời đúng
    public int getCorrectAnswers() {
        return sharedPreferences.getInt("correctAnswers", 0);
    }

    public void clearKanaStats() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
