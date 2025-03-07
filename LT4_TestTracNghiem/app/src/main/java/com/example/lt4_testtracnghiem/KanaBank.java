package com.example.lt4_testtracnghiem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum KanaBank {

    // Hiragana
    A("a", "あ"), I("i", "い"), U("u", "う"), E("e", "え"), O("o", "お"),
    KA("ka", "か"), KI("ki", "き"), KU("ku", "く"), KE("ke", "け"), KO("ko", "こ"),
    SA("sa", "さ"), SHI("shi", "し"), SU("su", "す"), SE("se", "せ"), SO("so", "そ"),
    TA("ta", "た"), CHI("chi", "ち"), TSU("tsu", "つ"), TE("te", "て"), TO("to", "と"),
    NA("na", "な"), NI("ni", "に"), NU("nu", "ぬ"), NE("ne", "ね"), NO("no", "の"),
    HA("ha", "は"), HI("hi", "ひ"), FU("fu", "ふ"), HE("he", "へ"), HO("ho", "ほ"),
    MA("ma", "ま"), MI("mi", "み"), MU("mu", "む"), ME("me", "め"), MO("mo", "も"),
    YA("ya", "や"), YU("yu", "ゆ"), YO("yo", "よ"),
    RA("ra", "ら"), RI("ri", "り"), RU("ru", "る"), RE("re", "れ"), RO("ro", "ろ"),
    WA("wa", "わ"), WO("wo", "を"), N("n", "ん"),

    // Katakana
    A_K("A", "ア"), I_K("I", "イ"), U_K("U", "ウ"), E_K("E", "エ"), O_K("O", "オ"),
    KA_K("Ka", "カ"), KI_K("Ki", "キ"), KU_K("Ku", "ク"), KE_K("Ke", "ケ"), KO_K("Ko", "コ"),
    SA_K("Sa", "サ"), SHI_K("Shi", "シ"), SU_K("Su", "ス"), SE_K("Se", "セ"), SO_K("So", "ソ"),
    TA_K("Ta", "タ"), CHI_K("Chi", "チ"), TSU_K("Tsu", "ツ"), TE_K("Te", "テ"), TO_K("To", "ト"),
    NA_K("Na", "ナ"), NI_K("Ni", "ニ"), NU_K("Nu", "ヌ"), NE_K("Ne", "ネ"), NO_K("No", "ノ"),
    HA_K("Ha", "ハ"), HI_K("Hi", "ヒ"), FU_K("Fu", "フ"), HE_K("He", "ヘ"), HO_K("Ho", "ホ"),
    MA_K("Ma", "マ"), MI_K("Mi", "ミ"), MU_K("Mu", "ム"), ME_K("Me", "メ"), MO_K("Mo", "モ"),
    YA_K("Ya", "ヤ"), YU_K("Yu", "ユ"), YO_K("Yo", "ヨ"),
    RA_K("Ra", "ラ"), RI_K("Ri", "リ"), RU_K("Ru", "ル"), RE_K("Re", "レ"), RO_K("Ro", "ロ"),
    WA_K("Wa", "ワ"), WO_K("Wo", "ヲ"), N_K("N", "ン");
    //Bảng kana gồm 2 thành phần là romanji và kana
    private final String romaji;    //là a, i, e
    private final String kana;      //là あ, い, う

    KanaBank(String romaji, String kana) {
        this.romaji = romaji;
        this.kana = kana;
    }

    public String getRomaji() {
        return romaji;
    }

    public String getKana() {
        return kana;
    }

    public static List<KanaBank> getAllKana()
    {
        //chuyển toàn bộ enum thành một List có phần tử kiểu KanaBank
        return Arrays.asList(KanaBank.values());
    }
}
