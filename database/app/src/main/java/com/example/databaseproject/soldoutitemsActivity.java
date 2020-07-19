package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class soldoutitemsActivity extends AppCompatActivity {

    TextView t1;

    DatabaseHelper1 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldoutitems);
        t1=findViewById(R.id.displaybill);
        db=new DatabaseHelper1(this);
        Cursor cursor=db.getview();
        if(cursor.getCount()==0)
            Toast.makeText(soldoutitemsActivity.this,"no transation there ",Toast.LENGTH_LONG).show();
        else
        {

            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                buffer.append(cursor.getColumnName(2)).append(" :").append(cursor.getString(2)).append("\n");
                buffer.append(cursor.getColumnName(3)).append(" :").append(cursor.getString(3)).append("\n");
                buffer.append(cursor.getColumnName(4)).append(" :").append(cursor.getString(4)).append("\n");
                buffer.append(".............................................................\n");
            }

            t1.setMovementMethod(new ScrollingMovementMethod());
            t1.setText(buffer.toString());
        }

    }
}
