package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class customerDetailsActivity extends AppCompatActivity {

    Button b1;
    EditText e1,e2,e3,e4;
    DatabaseHelper1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        b1=findViewById(R.id.customerregistration);
        e1=findViewById(R.id.customerid);
        e2=findViewById(R.id.customername);
        e3=findViewById(R.id.customerphonenumber);
        e4=findViewById(R.id.customerlocation);
        db=new DatabaseHelper1(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();
                if( s2.equals("") || s3.equals("") || s4.equals("") )
                    Toast.makeText(customerDetailsActivity.this,"fields are empty ",Toast.LENGTH_SHORT).show();
                else
                {
                    Boolean insertcust=db.insertintoCustomers(s1,s2,s3,s4);
                    if (insertcust==true) {
                        Toast.makeText(customerDetailsActivity.this, "Registration succesfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(customerDetailsActivity.this,homeActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(customerDetailsActivity.this,"not Registered",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
