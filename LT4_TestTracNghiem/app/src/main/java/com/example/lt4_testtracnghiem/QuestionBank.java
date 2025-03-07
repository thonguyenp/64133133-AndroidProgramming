package com.example.lt4_testtracnghiem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("mi", "み", List.of("む", "り", "み", "ぎ", "に", "も", "じ", "き")));
        questions.add(new Question("ka", "か", List.of("か", "き", "く", "け", "こ", "さ", "し", "す")));
        questions.add(new Question("su", "す", List.of("し", "す", "せ", "そ", "た", "ち", "つ", "て")));
        // Thêm nhiều câu hỏi khác vào đây

        Collections.shuffle(questions); // Xáo trộn câu hỏi
        return questions.subList(0, 10); // Chọn ngẫu nhiên 10 câu
    }

}
