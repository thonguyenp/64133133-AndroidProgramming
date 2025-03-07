package com.example.lt4_testtracnghiem;

import java.util.List;

public class Question {
    String romanji;
    String answer;
    List<String> options;

    public Question(String romanji, String answer, List<String> options) {
        this.romanji = romanji;
        this.answer = answer;
        this.options = options;
    }

    public String getRomanji() {
        return romanji;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }
}
