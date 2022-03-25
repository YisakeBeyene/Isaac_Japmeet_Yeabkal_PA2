package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {


    Button cancelButton;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        cancelButton = (Button) findViewById(R.id.cancel_button);
        titleTextView = (TextView) findViewById(R.id.title_textview);

        Intent intentFromMain = getIntent();
        String title = intentFromMain.getStringExtra("MONTH");
        titleTextView.setText("Title: " + title);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("mytag", title);

                Intent intent  = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}