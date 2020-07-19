package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class logsActivity extends AppCompatActivity {

    TextView t1;
    DatabaseHelper1 db;
    Button b1;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        t1=findViewById(R.id.logsfordeleted);
        b1=findViewById(R.id.buttonforrecover);
        e1=findViewById(R.id.idforrecover);
        db=new DatabaseHelper1(this);
        final Cursor cursor=db.getlogs();
        if(cursor.getCount()==0)
        {
            Toast.makeText(logsActivity.this,"no logs found",Toast.LENGTH_LONG).show();

        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            while(cursor.moveToNext())
            {
                buffer.append(cursor.getColumnName(0)).append(":").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(":").append(cursor.getString(1)).append("\n");
                buffer.append(cursor.getColumnName(2)).append(":").append(cursor.getString(2)).append("\n");
                buffer.append(cursor.getColumnName(3)).append(":").append(cursor.getString(3)).append("\n");
                buffer.append(cursor.getColumnName(4)).append(":").append(cursor.getString(4)).append("\n");
                buffer.append(cursor.getColumnName(5)).append(":").append(cursor.getString(5)).append("\n");
                buffer.append(".............................................\n");

            }

            t1.setText(buffer.toString());
            t1.setMovementMethod(new ScrollingMovementMethod());
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1=e1.getText().toString();
                if(s1.equals(""))
                    Toast.makeText(logsActivity.this,"enter id",Toast.LENGTH_SHORT).show();
                else {

                    Cursor cursor1=db.checkidfromlog(s1);
                    if(cursor1.getCount()==0)
                        Toast.makeText(logsActivity.this, "invalid id", Toast.LENGTH_SHORT).show();
                    else
                    {

                           Cursor cursor11=db.setmedicine(s1);
                           if(cursor11.getCount()==0)
                               Toast.makeText(logsActivity.this, "not set in medicine", Toast.LENGTH_SHORT).show();

                           Cursor cursor2=db.deletelog(s1);
                           if(cursor2.getCount()==0)
                               Toast.makeText(logsActivity.this, " recoverd successfully!!!", Toast.LENGTH_SHORT).show();


                        }

                    }


            }
        });



    }
}
