package com.example.isaac_yeabkal_pa2;

import java.io.Serializable;


//Isaac Beyene
//Japmeet Bendi
//Yeabkal Biru

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
