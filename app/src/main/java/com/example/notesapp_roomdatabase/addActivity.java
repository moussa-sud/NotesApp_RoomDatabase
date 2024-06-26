package com.example.notesapp_roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class addActivity extends AppCompatActivity {

    EditText title_input , author_input , pages_input ;
    Button add_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        pages_input = findViewById(R.id.pages_input);
        author_input = findViewById(R.id.author_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(addActivity.this);
                myDB.addBook(title_input.getText().toString(),author_input.getText().toString(), Integer.valueOf(pages_input.getText().toString())) ;
            }
        });

    }


}