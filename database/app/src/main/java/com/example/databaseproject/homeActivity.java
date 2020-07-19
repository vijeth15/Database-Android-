package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {

   // TextView e1;
    TextView t1;
    Button b1, b2, b3, b4, b5,b6,b7,b8,b9,b10,b11,b12;
    DatabaseHelper1 db;
    EditText e2, e3, e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        b1 = findViewById(R.id.buttonaddmedicine);
        b2 = findViewById(R.id.buttonviewavailable);
        b3 = findViewById(R.id.buttondeletemedicine);
        b4 = findViewById(R.id.buttonupdatemedicine);
        b5 = findViewById(R.id.buttonforcart);
        b6=findViewById(R.id.buttoncustomer);
        b7=findViewById(R.id.buttonshowcustomer);
        b8=findViewById(R.id.addcompany);
        b9=findViewById(R.id.showcompany);
        b10=findViewById(R.id.logs);
        b11=findViewById(R.id.updateordeletecompany);
        b12=findViewById(R.id.buttonsoldoutitems);
      //  e1 = findViewById(R.id.textView23);
        e2 = findViewById(R.id.fordeletion);
//        e3 = findViewById(R.id.goforcart);
//        e4 = findViewById(R.id.getquantity);
        t1=findViewById(R.id.gettextfromlogin);


        String usename=getIntent().getStringExtra("username");
        t1.setText(usename);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(homeActivity.this, medicineActivity.class);
                startActivity(intent);
            }
        });


        db = new DatabaseHelper1(this);
        final Cursor cursor = db.getfullproddetails();


        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No entry found", Toast.LENGTH_SHORT).show();
        }


//            final StringBuffer buffer = new StringBuffer();
//
//
//            String data;
//
//            while (cursor.moveToNext()) {
//                // String data="ID"+cursor.getString(0);
//                data = "ID " + cursor.getString(0) + "\n" +
//                        "NAME " + cursor.getString(1) + "\n" +
//                        "CATOGERY " + cursor.getString(2) + "\n" +
//                        "QUANTITY " + cursor.getString(3) + "\n" +
//                        "COST " + cursor.getString(4) + "\n" +
//                        "ANY TEXT  " + cursor.getString(5) + "\n";
//
//                buffer.append("").append(cursor.getString(0)).append("\t\t\t\t\t\t");
//                buffer.append("").append(cursor.getString(1)).append("\t\t\t\t\t\t");
//                // buffer.append("CATORARY  ").append(cursor.getString(2)).append("\n");
//                buffer.append("").append(cursor.getString(4)).append("\t\t\t\t\t\t");
//                buffer.append("").append(cursor.getString(3)).append("\n");
//
//            }
//
//
//           // e1.setText(buffer.toString());
//
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    StringBuffer buffer1 = new StringBuffer();
//                    buffer1.delete(0, buffer1.length());
//
//                    Cursor cursor1 = db.getfullproddetails();
//                    // cursor1.moveToFirst();
//                    while (cursor1.moveToNext()) {
//
//                        buffer1.append("").append(cursor1.getString(0)).append("\t\t\t\t\t");
//                        buffer1.append("").append(cursor1.getString(1)).append("\t\t\t\t\t");
//                        // buffer.append("CATORARY  ").append(cursor.getString(2)).append("\n");
//                        buffer1.append("").append(cursor1.getString(4)).append("\t\t\t\t\t");
//                        buffer1.append("").append(cursor1.getString(3)).append("\n");
//
//
//                    }
//                   // e1.setText(buffer1.toString());
                    Intent intent=new Intent(homeActivity.this,showDetailsActivity.class);
                    startActivity(intent);
                }


            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1=e2.getText().toString();


                   Cursor cursor1= db.setlog(s1);
                   StringBuffer buffer=new StringBuffer();
                   while (cursor1.moveToNext())
                   {
                       String s2=cursor1.getString(0).toString();
                       String s3=cursor1.getString(1).toString();
                       String s4=cursor1.getString(2).toString();
                       String s5=cursor1.getString(3).toString();
                       String s6=cursor1.getString(4).toString();
                       String s7=cursor1.getString(5).toString();
                       db.insertintologs(s2,s3,s4,s5,s6,s7);

                   }
                    Integer deleterow=db.delete(s1);
                    if(deleterow>0)
                        Toast.makeText(getApplicationContext(),"Deleted succesfully",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"ID is not found",Toast.LENGTH_SHORT).show();
                }
            });


            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(homeActivity.this,updateMedicineActivity.class);
                    startActivity(i);
                }
            });






            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "See in the cart", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(homeActivity.this,cartActivity.class);
                                startActivity(intent);
//                    String z=e3.getText().toString();
//                    String y=e4.getText().toString();
//                    if(z.equals(""))
//                        Toast.makeText(getApplicationContext(),"Enter id",Toast.LENGTH_SHORT).show();
//
//                    Boolean bz=db.checkid(z);
//                    if(bz==true) {
//
//                        Integer row=db.delete();
//                        if(row>=0) {
//                            Boolean k = db.idandquantity(z, y);
//                            if (k == true) {
//                                Toast.makeText(getApplicationContext(), "See in the cart", Toast.LENGTH_LONG).show();
//                                Intent intent=new Intent(homeActivity.this,cartActivity.class);
//                                startActivity(intent);
//                            }else
//                                Toast.makeText(getApplicationContext(), "insertion problem", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(),"its not emptyyyyyy",Toast.LENGTH_LONG).show();
//
//                    }
//                    else
//                        Toast.makeText(getApplicationContext(),"Invalid ID",Toast.LENGTH_LONG).show();
////                 //FragmentManager fragmentManager = getActivity().getFragmentManager();
////                 FragmentTransaction fr=getFragmentManager().beginTransaction();
//////                 fr.commit();
                }
            });


            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,customerDetailsActivity.class);
                    startActivity(intent);
                }
            });


            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,showcustomersActivity.class);
                    startActivity(intent);
                }
            });


            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,addcompanyActivity.class);
                    startActivity(intent);
                }
            });

            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,showcompanyActivity.class);
                    startActivity(intent);
                }
            });


            b10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,logsActivity.class);
                    startActivity(intent);
                }
            });


            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,updateordeleteActivity.class);
                    startActivity(intent);
                }
            });

            b12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(homeActivity.this,soldoutitemsActivity.class);
                    startActivity(intent);
                }
            });



    }
}
