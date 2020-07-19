package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateordeleteActivity extends AppCompatActivity {

    EditText editText,editText1,editText2,editText3;
    DatabaseHelper1 db;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateordelete);
    editText=findViewById(R.id.updatecompid);
    editText1=findViewById(R.id.updatecompname);
    editText2=findViewById(R.id.updatecompemail);
    editText3=findViewById(R.id.updatecomplocation);
    b1=findViewById(R.id.updateforcompany);
    b2=findViewById(R.id.deletecompany);
    db=new DatabaseHelper1(this);


    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s1=editText.getText().toString();
            String s2=editText1.getText().toString();
            String s3=editText2.getText().toString();
            String s4=editText3.getText().toString();
            if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals(""))
                Toast.makeText(updateordeleteActivity.this,"fields are empty",Toast.LENGTH_SHORT).show();
            else {
                db.updatethecompany(s1,s2,s3,s4);
                Toast.makeText(updateordeleteActivity.this,"successfully updated",Toast.LENGTH_SHORT).show();
            }

        }
    });

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s1=editText.getText().toString();
            String s2=editText1.getText().toString();
            String s3=editText2.getText().toString();
            String s4=editText3.getText().toString();

            if(s2.equals("")||s3.equals("")||s4.equals(""))
            {
                if(s1.equals(""))
                    Toast.makeText(updateordeleteActivity.this,"id should be added",Toast.LENGTH_SHORT).show();
                else{
                    Cursor cursor=db.checkcompid(s1);
                    if(cursor.getCount()==0){
                        Toast.makeText(updateordeleteActivity.this, "id not found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Integer z = db.deletecomp(s1);
                        if (z > 0)
                            Toast.makeText(updateordeleteActivity.this, "deleted successfully", Toast.LENGTH_SHORT).show();

                         else
                            Toast.makeText(updateordeleteActivity.this, "not deleted", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            else {
                Toast.makeText(updateordeleteActivity.this,"only id should be entered",Toast.LENGTH_SHORT).show();

            }

        }
    });






    }
}
