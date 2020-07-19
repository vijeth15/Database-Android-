package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //initalizations of variables
        e1=findViewById(R.id.editTextfirstname);
        e2=findViewById(R.id.editTextlastname);
        db = new DatabaseHelper(this);
        e3=findViewById(R.id.editTextemail);
        e4=findViewById(R.id.editTextpassword);
        b1=findViewById(R.id.buttonRegister);

        //set on click on register button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();

                if(s1.equals("")|| s2.equals("")||s3.equals("")||s4.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {



                    Boolean checkemail=db.checkmail(s3);
                    if(checkemail==true)
                    {

                        Boolean insertdata=db.inserts(s1,s2,s3,s4);
                        if(insertdata==true)
                        {

                            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,homeActivity.class);
                            intent.putExtra("username",s3);
                            startActivity(intent);

                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                }



            }
        });



    }

}
