package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Isaac Beyene
//Japmeet Bendi
//Yeabkal Biru

public class BookActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView authorTextView;
    EditText updateTitleEditText;
    EditText updateAuthorEditText;
    Button updateBookButton;
    Button deleteBookButton;

    Book book;
    BookSQLiteHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        titleTextView = (TextView) findViewById(R.id.title_textview);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        updateTitleEditText = (EditText) findViewById(R.id.update_title_editText);
        updateAuthorEditText = (EditText) findViewById(R.id.update_author_editText);
        updateBookButton = (Button) findViewById(R.id.update_book_button);
        deleteBookButton = (Button) findViewById(R.id.delete_book_button);

        getAndSetIntentData();


        //Onclick listener for the Update Book button
        updateBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book updatedBook = new Book(updateTitleEditText.getText().toString(), updateAuthorEditText.getText().toString());
                updatedBook.setId(book.getId());
                myDB = new BookSQLiteHelper(BookActivity.this);
                myDB.updateBook(updatedBook);

                Intent intent  = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Onclick listener for the Delete Book button
        deleteBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book deletedBook = new Book(updateTitleEditText.getText().toString(), updateAuthorEditText.getText().toString());
                deletedBook.setId(book.getId());
                myDB = new BookSQLiteHelper(BookActivity.this);
                myDB.deleteBook(deletedBook);

                Intent intent  = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Method to get the name and title of the book clicked and display them
    private void getAndSetIntentData() {
        Intent intentFromMain = getIntent();
        book = (Book) intentFromMain.getSerializableExtra("BOOK");
        titleTextView.setText("Title: " + book.getTitle());
        authorTextView.setText("Author: " + book.getAuthor());
        updateTitleEditText.setText(book.getTitle());
        updateAuthorEditText.setText(book.getAuthor());
    }
}