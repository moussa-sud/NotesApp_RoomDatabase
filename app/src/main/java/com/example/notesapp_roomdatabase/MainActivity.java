package com.example.notesapp_roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button ;

    MyDatabaseHelper myDB ;
    CustomAdapter customAdapter;

    ArrayList<String> book_id , book_title, book_author , book_pages ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.recyclerView);

       add_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(MainActivity.this , addActivity.class);
               startActivity(intent);

           }
       });

        myDB= new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_title = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this , book_id,book_title, book_author,book_pages);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount()== 0 ){

            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();

        }else {
            while (cursor.moveToNext()){

                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));


            }
        }
    }
}