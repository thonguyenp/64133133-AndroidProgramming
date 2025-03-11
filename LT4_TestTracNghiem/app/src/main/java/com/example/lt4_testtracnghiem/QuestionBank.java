package com.example.lt4_testtracnghiem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuestionBank {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        List<KanaBank> allKana = new ArrayList<>(KanaBank.getAllKana());
        Random random = new Random();
        Collections.shuffle(allKana);
        for (int i = 0; i < 10; i++)
        {
            KanaBank kana = allKana.get(i); //lấy chữ kana thứ i trong danh sách đã xáo
            String romanjiDung = kana.getRomaji();
            String kanaDung = kana.getKana();

            //Tạo 1 set options chứa các đáp án khác nhau
            Set<String> options = new HashSet<>();  //dùng set thay cho list để tránh xuất hiện 2 đáp án đúng
            options.add(kanaDung);  //thêm đáp án đúng vào set
            //vòng lặp thêm 7 đáp án sai còn lại
            while (options.size() < 8) {
                //chọn ngẫu nhiên 1 chữ trong list (list có 92 phần tử) nhưng không trùng với đáp án
                KanaBank randomKana = allKana.get(random.nextInt(allKana.size()));
                options.add(randomKana.getKana());  //thêm chữ đó vào set
            }
            List<String> optList = new ArrayList<>(options);    //copy set thành list vì set ko có thứ tự
            Collections.shuffle(optList);   //xóa trộn list
            questions.add(new Question(romanjiDung,kanaDung,optList)); //1 câu hỏi gồm 1 đáp án đúng và 1 list các đáp án khác nhau
        }
        return questions;
    }
}
