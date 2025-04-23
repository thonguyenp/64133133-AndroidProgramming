package com.example.finalproject_basicjaplearning;

public enum KanjiBank {
    NICHI("日", "Nhật", "ngày", "ニチ", "ひ"),
    ICHI("一", "Nhất", "một", "イチ", "ひと"),
    JIN("人", "Nhân", "người", "ジン", "ひと"),
    GETSU("月", "Nguyệt", "tháng, mặt trăng", "ゲツ", "つき"),
    KA("火", "Hỏa", "lửa", "カ", "ひ"),
    SUI("水", "Thủy", "nước", "スイ", "みず"),
    MOKU("木", "Mộc", "cây", "モク", "き"),
    KIN("金", "Kim", "vàng, tiền", "キン", "かね"),
    DO("土", "Thổ", "đất", "ド", "つち");
    // 👉 Thêm các chữ Kanji N5 khác vào đây nếu bạn muốn

    public final String kanji;
    public final String hanViet;
    public final String meaning;
    public final String onReading;
    public final String kunReading;

    KanjiBank(String kanji, String hanViet, String meaning, String onReading, String kunReading) {
        this.kanji = kanji;
        this.hanViet = hanViet;
        this.meaning = meaning;
        this.onReading = onReading;
        this.kunReading = kunReading;
    }

}
