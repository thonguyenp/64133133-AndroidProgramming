package com.example.lt4_testtracnghiem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("mi", "み", List.of("む", "り", "み", "ぎ", "に", "も", "じ", "き")));
        questions.add(new Question("ka", "か", List.of("か", "き", "く", "け", "こ", "さ", "し", "す")));
        questions.add(new Question("su", "す", List.of("し", "す", "せ", "そ", "た", "ち", "つ", "て")));
        questions.add(new Question("a", "あ", List.of("し", "す", "あ", "そ", "た", "ち", "つ", "て")));
        questions.add(new Question("ha", "は", List.of("し", "す", "あ", "は", "た", "ち", "つ", "て")));
        questions.add(new Question("ku", "ク", List.of("し", "す", "あ", "は", "た", "ち", "ク", "て")));

        // Thêm nhiều câu hỏi khác vào đây
        Collections.shuffle(questions); // Xáo trộn câu hỏi
        return questions.subList(0, 6); // Chọn ngẫu nhiên 10 câu
    }

}
