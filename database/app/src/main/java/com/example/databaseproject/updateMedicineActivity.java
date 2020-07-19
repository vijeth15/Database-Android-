package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateMedicineActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    DatabaseHelper1 db;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);


        e1=findViewById(R.id.idforupdation);
        e2=findViewById(R.id.quantityforupdation);
        e3=findViewById(R.id.costforupdation);
        e4=findViewById(R.id.nameforupdation);
        b1=findViewById(R.id.buttonforupdation);
        db=new DatabaseHelper1(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e4.getText().toString();
                String s3=e2.getText().toString();
                String s4=e3.getText().toString();

                if(s1.equals(""))
                    Toast.makeText(getApplicationContext(),"ID IS MANDITORY ",Toast.LENGTH_LONG).show();
                else
                {
                    Boolean b1=db.checkid(s1);
                    if(b1==true)
                    {

                        Boolean b2=db.update(s1,s2,s3,s4);
                        if(b2==true)
                        {

                            Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_LONG).show();
                        }




                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"INVALID ID",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}
