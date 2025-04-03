package com.example.tuonthigklan1;

public class LandScape {
    String imgFile;
    String landCaption;

    public LandScape(String imgFile, String landCaption) {
        this.imgFile = imgFile;
        this.landCaption = landCaption;
    }

    public String getImgFile() {
        return imgFile;
    }

    public String getLandCaption() {
        return landCaption;
    }
}
