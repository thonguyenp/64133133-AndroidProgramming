package com.example.finalproject_basicjaplearning;

public enum KanaBank {
    A("a", "あ","ア"), I("i", "い","イ"), U("u", "う","ウ"), E("e", "え","エ"), O("o", "お","オ"),
    KA("ka", "か","カ"), KI("ki", "き","キ"), KU("ku", "く","ク"), KE("ke", "け","ケ"), KO("ko", "こ","コ"),
    SA("sa", "さ","サ"), SHI("shi", "し","シ"), SU("su", "す","ス"), SE("se", "せ","セ"), SO("so", "そ","ソ"),
    TA("ta", "た","タ"), CHI("chi", "ち", "チ"), TSU("tsu", "つ","ツ"), TE("te", "て","テ"), TO("to", "と","ト"),
    NA("na", "な","ナ"), NI("ni", "に","ニ"), NU("nu", "ぬ","ヌ"), NE("ne", "ね","ネ"), NO("no", "の","ノ"),
    HA("ha", "は","ハ"), HI("hi", "ひ","ヒ"), FU("fu", "ふ","フ"), HE("he", "へ","ヘ"), HO("ho", "ほ","ホ"),
    MA("ma", "ま","マ"), MI("mi", "み","ミ"), MU("mu", "む","ム"), ME("me", "め","メ"), MO("mo", "も","モ"),
    RA("ra", "ら","ラ"), RI("ri", "り","リ"), RU("ru", "る","ル"), RE("re", "れ","レ"), RO("ro", "ろ","ロ"),
    YA("ya", "や","ヤ"), YU("yu", "ゆ","ユ"), YO("yo", "よ","ヨ"),
    WA("wa", "わ","ワ"), WO("wo", "を","ヲ"),
    N("n", "ん","ン");



    public final String romaji;
    public final String hiragana;
    public final String katakana;

    KanaBank(String romaji, String hiragana, String katakana) {
        this.romaji = romaji;
        this.hiragana = hiragana;
        this.katakana = katakana;
    }

}
