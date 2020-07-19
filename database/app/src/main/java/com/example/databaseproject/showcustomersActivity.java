package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class showcustomersActivity extends AppCompatActivity {

    TextView t1,t2;
    DatabaseHelper1 db;
    Button b1;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcustomers);
        t1=findViewById(R.id.displaycustomers);
        t2=findViewById(R.id.countofcustomerss);
        db=new DatabaseHelper1(this);
        b1=findViewById(R.id.buttonfromcusttoprod);
        e1=findViewById(R.id.editTextfromcusttoprod);


        final Cursor cursor=db.displaycustomers();
        if(cursor.getCount()==0)
        {
            Toast.makeText(showcustomersActivity.this,"NO CUSTOMERS FOUND",Toast.LENGTH_LONG).show();

        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                buffer.append(cursor.getColumnName(2)).append(" :").append(cursor.getString(2)).append("\n");
                buffer.append(cursor.getColumnName(3)).append(" :").append(cursor.getString(3)).append("\n");
                buffer.append(".......................................................\n");
            }
            t1.setMovementMethod(new ScrollingMovementMethod());
            t1.setText(buffer.toString());
        }

       int x=db.countofcustomers();
        if(x==0)
        {
            Toast.makeText(showcustomersActivity.this,"no customers found",Toast.LENGTH_LONG).show();

        }
        else {
            String xx=String.valueOf(x);
            t2.setText(xx);
            Toast.makeText(showcustomersActivity.this,"count is "+x,Toast.LENGTH_LONG).show();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                if (s1.equals(""))
                    Toast.makeText(showcustomersActivity.this,"enter id",Toast.LENGTH_SHORT).show();
                else
                {
                        Cursor cursor1=db.checkidfromcust(s1);
                        if(cursor1.getCount()==0)
                            Toast.makeText(showcustomersActivity.this,"id not present",Toast.LENGTH_SHORT).show();

                        else{

                            Intent intent=new Intent(showcustomersActivity.this,custtoinfoActivity.class);
                            intent.putExtra("id",s1);
                            startActivity(intent);
                        }
                            //Toast.makeText(showcustomersActivity.this,"id is present",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
