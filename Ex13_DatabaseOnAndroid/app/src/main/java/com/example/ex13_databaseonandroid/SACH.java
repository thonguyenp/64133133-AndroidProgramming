package com.example.ex13_databaseonandroid;

public class SACH {
    int BookID;
    String BookName;
    int page;
    float price;
    String Description;

    public SACH(int bookID, String bookName, int page, float price, String description) {
        BookID = bookID;
        BookName = bookName;
        this.page = page;
        this.price = price;
        Description = description;
    }
}
