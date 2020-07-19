package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class showDetailsActivity extends AppCompatActivity {

    DatabaseHelper1 db;
    TextView e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        e1 = findViewById(R.id.displayProd);
        db = new DatabaseHelper1(this);
        final Cursor cursor = db.getfullproddetails();


        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No entry found", Toast.LENGTH_SHORT).show();
        } else {

            final StringBuffer buffer = new StringBuffer();


            String data;

            while (cursor.moveToNext()) {
                // String data="ID"+cursor.getString(0);
                data = "ID " + cursor.getString(0) + "\n" +
                        "NAME " + cursor.getString(1) + "\n" +
                        "CATOGERY " + cursor.getString(2) + "\n" +
                        "QUANTITY " + cursor.getString(3) + "\n" +
                        "COST " + cursor.getString(4) + "\n" +
                        "ANY TEXT  " + cursor.getString(5) + "\n";

                buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                // buffer.append("CATORARY  ").append(cursor.getString(2)).append("\n");
                buffer.append(cursor.getColumnName(3)).append(" :").append(cursor.getString(3)).append("\n");
                buffer.append("company id").append(" :").append(cursor.getString(4)).append("\n");
                buffer.append("..................................................");

            }
            //db.execSQL("create table if not exists medicine(productid text primary key,productname text,category text, quantity text ,price text,anytext text references company(companyid) on delete cascade)");
            e1.setMovementMethod(new ScrollingMovementMethod());
            e1.setText(buffer.toString());
        }

        StringBuffer buffer1=new StringBuffer();
        buffer1.delete(0,buffer1.length());

        Cursor cursor1= db.getfullproddetails();
        // cursor1.moveToFirst();
        while (cursor1.moveToNext()) {

            buffer1.append(cursor1.getColumnName(0)).append(" :").append(cursor1.getString(0)).append("\n");
            buffer1.append(cursor1.getColumnName(1)).append(" :").append(cursor1.getString(1)).append("\n");
            // buffer.append("CATORARY  ").append(cursor.getString(2)).append("\n");
            buffer1.append(cursor1.getColumnName(3)).append(" :").append(cursor1.getString(3)).append("\n");
            buffer1.append(cursor1.getColumnName(4)).append(" :").append(cursor1.getString(4)).append("\n");
            buffer1.append("company id").append(" :").append(cursor1.getString(5)).append("\n");
            buffer1.append("..............................................\n");
        }
        e1.setText(buffer1.toString());
    }
    }
