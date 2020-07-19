package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medicineActivity extends AppCompatActivity {

    Button b1;
    DatabaseHelper1 db1;
    EditText e1,e2,e3,e4,e5,e6;
    EditText e7,e8,e9,e10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        b1=findViewById(R.id.insertbutton);
        e1=findViewById(R.id.productid);
        e2=findViewById(R.id.productname);
        e3=findViewById(R.id.category);
        e4=findViewById(R.id.quantity);
        e5=findViewById(R.id.price);
        e6=findViewById(R.id.anytext);
        //new

        //new over
        //int productid,String productname,String category,int quantity,int price
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();
                String s5=e5.getText().toString();
                String s6=e6.getText().toString();
                //new

                //new over


                db1=new DatabaseHelper1(getApplicationContext());


                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")/*new one from here */)
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                else
                {
                           Boolean zz=db1.insert(s1,s2,s3,s4,s5,s6);
                           if(zz==false)
                               Toast.makeText(medicineActivity.this,"not added becz there is no company present of that id !!!",Toast.LENGTH_LONG).show();
                           else {
                               Toast.makeText(getApplicationContext(), "added succesfully", Toast.LENGTH_SHORT).show();
                           }
                }
            }
        });
    }
}
