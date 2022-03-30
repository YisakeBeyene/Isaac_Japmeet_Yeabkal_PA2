package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    FloatingActionButton fab;

    BookSQLiteHelper myDB;
    ArrayList<Book> listOfBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfBooks = new ArrayList<>();
        myDB = new BookSQLiteHelper(MainActivity.this);
        listOfBooks = myDB.getAllBooks();
        adapter = new RecyclerAdapter(this, listOfBooks);
        recyclerView.setAdapter(adapter);


        printListOfBooks(listOfBooks);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printListOfBooks(listOfBooks);
                Intent intent = new Intent(MainActivity.this, AddNewBook.class);
                startActivity(intent);
            }
        });

    }

    private void printListOfBooks(ArrayList<Book> listOfBooks) {

        for(int i=0; i<listOfBooks.size(); i++){
            Book book = listOfBooks.get(i);
            System.out.println("Book at index " + i);
            System.out.println("Book at index " + book.getTitle());
            System.out.println("Book at index " + book.getAuthor());
        }

    }
}