package com.example.ex10_recyclerview;

public class LandScape {
    String landImgFileName;
    String landCaption;

    public LandScape(String landImgFileName, String landCaption) {
        this.landImgFileName = landImgFileName;
        this.landCaption = landCaption;
    }

    public String getLandImgFileName() {
        return landImgFileName;
    }

    public void setLandImgFileName(String landImgFileName) {
        this.landImgFileName = landImgFileName;
    }

    public String getLandCaption() {
        return landCaption;
    }

    public void setLandCaption(String landCaption) {
        this.landCaption = landCaption;
    }
}
