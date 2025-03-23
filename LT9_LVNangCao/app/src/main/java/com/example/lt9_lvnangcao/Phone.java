package com.example.lt9_lvnangcao;

public class Phone {
    private int img;
    private String name;
    private int giaTien, soLuong;

    //Sau khi đặt tên cho các thuộc tính thì tạo constructor và get/set
    public Phone(int img, String name, int giaTien, int soLuong) {
        this.img = img;
        this.name = name;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
