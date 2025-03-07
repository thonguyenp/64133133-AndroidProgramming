package com.example.lt4_testtracnghiem;

import java.util.List;

public class Question {
    String question;
    String answer;
    List<String> options;

    public Question(String question, String answer, List<String> options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }
}
