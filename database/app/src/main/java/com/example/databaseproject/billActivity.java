package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class billActivity extends AppCompatActivity {

    TextView t1,t2;
    DatabaseHelper1 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        t1=findViewById(R.id.finalamountinbill);
        t2=findViewById(R.id.showdetailsofbill);

        String s1=getIntent().getStringExtra("totalamount");
        t1.setText(s1);
        db=new DatabaseHelper1(this);
        Cursor cursor=db.getdatafrombill();
        StringBuffer buffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
            buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
            buffer.append("customer id").append(" :").append(cursor.getString(2)).append("\n");
            buffer.append(cursor.getColumnName(3)).append(" :").append(cursor.getString(3)).append("\n");
            buffer.append(cursor.getColumnName(4)).append(" :").append(cursor.getString(4)).append("\n");
            buffer.append("..............................................................\n");
        }

    t2.setMovementMethod(new ScrollingMovementMethod());
        t2.setText(buffer.toString());
    }
}
