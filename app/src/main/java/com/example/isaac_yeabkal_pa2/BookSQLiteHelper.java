package com.example.isaac_yeabkal_pa2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookSQLiteHelper extends SQLiteOpenHelper {

    public BookSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addBook(Book book) {

    }

    public Book searchBook(int id) {

        return null;
    }

    public ArrayList<Book> getAllBooks() {

        return null;
    }

    public void updateBook(Book book) {

    }

    public void deleteBook(Book book) {

    }
}
