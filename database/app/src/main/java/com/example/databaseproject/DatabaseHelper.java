package com.example.databaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user" +
                "(fname text," +
                "lname text," +
                "email text," +
                "password text)" +
                "");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop  table if exists user");
    }
    //inserting in database
    public  boolean inserts(String fname, String lname,String email,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("lname",lname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins=db.insert("user","null",contentValues);

        if(ins==-1)
            return false;
        else
            return true;
    }
    //checking the existance of the email;

    public boolean checkmail(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return false;

        else
            return true;

    }

    //checking the existance of gmail and password;


    public boolean emailpass(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from user where email=? and password=?",new String[]{email,password});

        if (cursor.getCount()>0)
            return true;

        else
            return false;
    }
}
