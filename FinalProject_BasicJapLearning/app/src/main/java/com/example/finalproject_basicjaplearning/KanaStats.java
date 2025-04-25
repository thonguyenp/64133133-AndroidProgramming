package com.example.finalproject_basicjaplearning;

public class KanaStats {
    private int correctCount;
    private int incorrectCount;

    public KanaStats() {
        this.correctCount = 0;
        this.incorrectCount = 0;
    }

    public void incrementCorrect() {
        this.correctCount++;
    }

    public void incrementIncorrect() {
        this.incorrectCount++;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public double getCorrectRate() {
        int total = correctCount + incorrectCount;
        return total == 0 ? 0 : (double) correctCount / total * 100;
    }

}
