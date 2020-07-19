package com.example.databaseproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    DatabaseHelper db;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.loginEmail);
        e2=findViewById(R.id.loginPassword);
        db=new DatabaseHelper(this);
        b1=findViewById(R.id.loginLogin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                Boolean chkemailpass=db.emailpass(s1,s2);

                if(s1.equals("") || s2.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (chkemailpass == true) {
                        Toast.makeText(getApplicationContext(), "successfull login ", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,homeActivity.class);
                        i.putExtra("username",s1);
                        startActivity(i);



                    }

                    else
                        Toast.makeText(getApplicationContext(), "wrong email or password ", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    public void gotoregiter(View view) {
        Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);


    }
}

