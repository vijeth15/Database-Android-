package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class custtoinfoActivity extends AppCompatActivity {

    TextView t1;
    DatabaseHelper1 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custtoinfo);
        t1=findViewById(R.id.displyproname);
        String s1=getIntent().getStringExtra("id");
        db=new DatabaseHelper1(this);
        Cursor cursor=db.dispthecustprod(s1);
        if(cursor.getCount()==0)
            Toast.makeText(custtoinfoActivity.this,"no products available",Toast.LENGTH_SHORT).show();
        else
        {
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                buffer.append(".........................................\n");
            }

            t1.setMovementMethod(new ScrollingMovementMethod());
            t1.setText(buffer.toString());
        }


    }
}
