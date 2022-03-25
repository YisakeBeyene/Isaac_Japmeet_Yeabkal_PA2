package com.example.isaac_yeabkal_pa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNewBook extends AppCompatActivity {

    Button cancleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        cancleButton = (Button) findViewById(R.id.cancel_button_newbook);
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewBook.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}