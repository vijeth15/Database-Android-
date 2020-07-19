package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CursorTreeAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class showcompanyActivity extends AppCompatActivity {

    DatabaseHelper1 db;
    Button b1;
    EditText e1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcompany);
        t1=findViewById(R.id.showcompanydetails);
        db=new DatabaseHelper1(this);
        b1=findViewById(R.id.productsofthatcomp);
        e1=findViewById(R.id.idforproductofcomp);


        Cursor cursor=db.displaycompany();
        if(cursor.getCount()==0)
        {
            Toast.makeText(showcompanyActivity.this,"no companies",Toast.LENGTH_LONG).show();

        }
        else{
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext())
            {
//                buffer.append("\t").append(cursor.getString(0)).append("\t").append("\t").append("\t").append("\t");
//                buffer.append("\t").append(cursor.getString(1)).append("\t").append("\t").append("\t").append("\t");
//                buffer.append("\t").append(cursor.getString(2)).append("\t").append("\t").append("\t").append("\t");
//                buffer.append("\t").append(cursor.getString(3)).append("\n");

                buffer.append(cursor.getColumnName(0)).append("\t :").append(cursor.getString(0)).append("\n");
                buffer.append(cursor.getColumnName(1)).append("\t :").append(cursor.getString(1)).append("\t").append("\n");
                buffer.append(cursor.getColumnName(2)).append("\t :").append(cursor.getString(2)).append("\t").append("\n");
                buffer.append(cursor.getColumnName(3)).append("\t :").append(cursor.getString(3)).append("\n");
                buffer.append("..........................................................\n");
            }
            t1.setMovementMethod(new ScrollingMovementMethod());

            t1.setText(buffer.toString());

        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                if(s1.equals(""))
                    Toast.makeText(showcompanyActivity.this,"not entired the filed",Toast.LENGTH_SHORT).show();
                else
                {
                    Cursor cursor1=db.checkcompid(s1);
                    if(cursor1.getCount()>0){
                    Intent intent=new Intent(showcompanyActivity.this,fromcompanttoproductActivity.class);
                    intent.putExtra("companyid",s1);
                    startActivity(intent);}
                    else
                        Toast.makeText(showcompanyActivity.this,"ID is not present",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
