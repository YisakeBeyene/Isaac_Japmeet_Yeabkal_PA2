package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewBook extends AppCompatActivity {

    EditText etTitle, etAuthor;
    Button addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        etTitle = (EditText) findViewById(R.id.update_title_editText);
        etAuthor = (EditText) findViewById(R.id.editTextAuthor);
        addBookButton = (Button) findViewById(R.id.add_book_button);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookSQLiteHelper myDB = new BookSQLiteHelper(AddNewBook.this);
                Book newBook = new Book(etTitle.getText().toString().trim(),
                        etAuthor.getText().toString().trim());
                myDB.addBook(newBook);
                Intent intent = new Intent(AddNewBook.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}