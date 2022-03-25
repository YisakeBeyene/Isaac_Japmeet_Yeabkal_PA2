package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    String monthNames[] = {"January","February","March","April","May","June","July","August","September","October","November","December",
            "January","February","March","April","May","June","July",  "August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this, monthNames);
        recyclerView.setAdapter(adapter);

    }
}