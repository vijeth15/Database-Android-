package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fromcompanttoproductActivity extends AppCompatActivity {

    DatabaseHelper1 db;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fromcompanttoproduct);
        String s1=getIntent().getStringExtra("companyid");
        db=new DatabaseHelper1(this);
        t1=findViewById(R.id.displayproductfromc);

        Cursor cursor=db.getproductfromcompany(s1);
        if(cursor.getCount()==0)
            Toast.makeText(fromcompanttoproductActivity.this,"no product found",Toast.LENGTH_SHORT).show();
        else
        {
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                buffer.append("...................................\n");
            }
            t1.setMovementMethod(new ScrollingMovementMethod());
            t1.setText(buffer.toString());


        }

    }
}
