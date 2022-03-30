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
        db = getWritableDatabase();
        String query  = "CREATE TABLE " + TABLE_NAME + " (" +
                         KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         KEY_ROWTITLE + " TEXT NOT NULL, " +
                         KEY_ROWAUTHOR + " TEXT NOT NULL);";

        try{
            db.execSQL(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        initDatabase(db);

    }

    // Add sample data when first creating database
    protected void initDatabase(SQLiteDatabase db) {
        ArrayList<Book> bookListSample = new ArrayList<>();

        bookListSample.add(new Book("The Hobbit", "J R R Tolkien"));
        bookListSample.add(new Book("The Da Vinci Code", "Dan Brown"));
        bookListSample.add(new Book("The Official Highway Code", "Department for Transport"));
        bookListSample.add(new Book("Fifty Shades of Grey", "E L James"));
        bookListSample.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        bookListSample.add(new Book("Jamie’s 15 minute meals", "Jamie Oliver"));
        bookListSample.add(new Book("The BFG", "Roald Dahl"));
        bookListSample.add(new Book("Great Expectations", "Charles Dickens"));
        bookListSample.add(new Book("Animal Farm", "George Orwell"));
        bookListSample.add(new Book("1984", "George Orwell"));

        for (Book book : bookListSample) {
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
