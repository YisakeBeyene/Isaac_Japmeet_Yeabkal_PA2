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

    private static BookSQLiteHelper dbInstance;

    static final String DATABASE_NAME = "BookLibrary.db";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "book_library";
    static final String TAG = "BookSQLiteHelper";
    static final String KEY_ROWID = "_id";
    static final String KEY_ROWTITLE = "book_title";
    static final String KEY_ROWAUTHOR = "book_author";

    private Context context;
    SQLiteDatabase db;

    String[] starterBookTitle = {"The Hobbit", "The Da Vinci Code", "The Official Highway Code", "Fifty Shades of Grey", "To Kill a Mockingbird", "Jamie’s 15 minute meals", "Great Expectations", "Animal Farm", "1984", "The BFG", "Harry Potter and the Goblet of Fire", "Harry Potter and the Prisoner of Azkaban", "The Lion, The Witch and The Wardrobe", "Pride and Prejudice", "The Hitchhiker's Guide to the Galaxy", "The Girl with the Dragon Tattoo", "Bridget Jones's Diary", "Little Women", "Romeo and Juliet", "Dracula", "The Secret Garden", "George's Marvellous Medicine", "Time Travellers Wife", "The Hunger Games", "The Catcher in the Rye", "David Copperfield"};
    String[] starterBookAuthor = {"J R R Tolkien", "Dan Brown", "Department for Transport", "E L James", "Harper Lee", "Jamie Oliver", "Charles Dickens", "George Orwell", "George Orwell", "Roald Dahl", "JK Rowling", "JK Rowling", "CS Lewis", "Jane Austen", "JRR Tolkein", "Douglas Adams", "Stieg Larsson", "Helen Fielding", "Louisa May Alcott", "William Shakespeare", "Bram Stoker", "Frances Hodgson Burnett", "Roald Dahl", "Audrey Niffenegger", "Suzanne Collins", "J.D Salinger", "Charles Dickens"};

    public static synchronized BookSQLiteHelper getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new BookSQLiteHelper(context.getApplicationContext());
        }
        return dbInstance;
    }

    public BookSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query  = "CREATE TABLE " + TABLE_NAME + " (" +
                         KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         KEY_ROWTITLE + " TEXT NOT NULL, " +
                         KEY_ROWAUTHOR + " TEXT NOT NULL);";

        try{
            db.execSQL(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        for(int i=0; i<starterBookTitle.length; i++){
            Book book = new Book(starterBookTitle[i], starterBookAuthor[i]);
            insertBookDB(db, book);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
               + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertBookDB(SQLiteDatabase db, Book book) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWTITLE, book.getTitle());
        cv.put(KEY_ROWAUTHOR, book.getAuthor());

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfull", Toast.LENGTH_SHORT).show();
        }
    }

    public void addBook(Book newBook) {
        db = this.getWritableDatabase();

        insertBookDB(db, newBook);
        db.close();
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
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_ROWTITLE, book.getTitle());
        cv.put(KEY_ROWAUTHOR, book.getAuthor());

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[] {String.valueOf(book.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfull", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBook(Book book) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ROWID + "=" + book.getId(), null);
    }
}
