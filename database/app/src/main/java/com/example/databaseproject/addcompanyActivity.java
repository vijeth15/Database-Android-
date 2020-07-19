package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addcompanyActivity extends AppCompatActivity {
    DatabaseHelper1 db;
    Button b1;
    EditText e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompany);
        e1=findViewById(R.id.companyid);
        e2=findViewById(R.id.companyname);
        e3=findViewById(R.id.companyemail);
        e4=findViewById(R.id.companylocation);
        b1=findViewById(R.id.submitforcompany);
        db=new DatabaseHelper1(this);





    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s1,s2,s3,s4;
            s1=e1.getText().toString();
            s2=e2.getText().toString();
            s3=e3.getText().toString();
            s4=e4.getText().toString();
            Boolean c=db.insertintocompany(s1,s2,s3,s4);
            if(c)
                Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplicationContext(),"Not added",Toast.LENGTH_LONG).show();
            }


        }
    });


    }


}
