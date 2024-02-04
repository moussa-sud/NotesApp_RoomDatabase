package com.example.notesapp_roomdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context ;
    public static String BATABASE_NAME = "BookLibrary.db";
    public static int  DATABASE_VERSION =  1;

    public static String TABLE_NAME = "my_library";
    public static String Column_ID = "_id";
    public static String Column_TITLE = "book_title";
    public static String Column_AUTOR = "book_author";
    public static String Column_PAGES = "book_pages";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, BATABASE_NAME , null , DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_TITLE + " TEXT, " +
                Column_AUTOR + " TEXT, " +
                Column_PAGES + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public void addBook(String title , String author , int pages ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues ();

        cv.put(Column_TITLE, title );
        cv.put(Column_AUTOR, author );
        cv.put(Column_PAGES, pages );

        Long result = db.insert(TABLE_NAME, null, cv );

        if (result == -1){
            Toast.makeText(context , "Failed", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context , "Added Successfully!", Toast.LENGTH_LONG).show();
        }

    }

    Cursor readAllData(){

        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null ;
        if(db != null){
            return cursor = db.rawQuery(query, null );

        }
        return cursor ;
    }

}
