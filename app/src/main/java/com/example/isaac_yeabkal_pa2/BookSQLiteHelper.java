package com.example.isaac_yeabkal_pa2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "BookLibrary.db";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "book_library";
    static final String TAG = "BookSQLiteHelper";
    static final String KEY_ROWID = "_id";
    static final String KEY_ROWTITLE = "book_title";
    static final String KEY_ROWAUTHOR = "book_author";

    private Context context;
    SQLiteDatabase db;

    public BookSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query  = "create table " + TABLE_NAME +
                                     " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                     KEY_ROWTITLE + " TEXT NOT NULL, " +
                                     KEY_ROWAUTHOR + " TEXT NOT NULL);";

        try{
            db.execSQL(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
               + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(Book newBook) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_ROWTITLE, newBook.getTitle());
        cv.put(KEY_ROWAUTHOR, newBook.getAuthor());
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfull", Toast.LENGTH_SHORT).show();
        }
    }

    public Book searchBook(int id) {

        return null;
    }

    public ArrayList<Book> getAllBooks() {

        String query = "SELECT * FROM " + TABLE_NAME;
        ArrayList<Book> listOfBooks = new ArrayList<>();
        db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String titleName = cursor.getString(1);
            String authorName = cursor.getString(2);
            Book book = new Book(titleName, authorName);
            book.setId(id);
            listOfBooks.add(book);
        }

        return listOfBooks;
    }

    public void updateBook(Book book) {

    }

    public void deleteBook(Book book) {

    }
}
