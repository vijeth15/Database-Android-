package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class cartActivity extends AppCompatActivity {

    DatabaseHelper1 db;
   TextView t1,t2;
   EditText e1,e2,e3;
   Button b1,b2,b3,b4;
   int sum=0;
   int x=0;
   String id,quantity;
    String idforcus;

//TextView t1;
//    SearchView searchView;
//    ListView listView;

//
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

            t1=findViewById(R.id.displaycart);
            t2=findViewById(R.id.thetotalamount);
            db=new DatabaseHelper1(this);
//        listView=findViewById(R.id.mylist);
  //      t1=findViewById(R.id.quantity11);
//        searchView=findViewById(R.id.searchview);

      //  Cursor cursor=db.getfullproddetails();



//        if(cursor.getCount()==0)
//        {
//            Toast.makeText(cartActivity.this,"no Entry found ",Toast.LENGTH_SHORT);
//
//        }
//
//
//
//        else
//        {
//            final StringBuffer buffer=new StringBuffer();
//            //cursor.moveToFirst();
//            while (cursor.moveToNext())
//            {
//               // buffer.append(cursor.getString(0)).append("\t");
//                buffer.append(cursor.getString(1)).append("\n");
////                buffer.append(cursor.getString(2)).append("\t");
////                buffer.append(cursor.getString(3)).append("\t");
////                buffer.append(cursor.getString(4)).append("\t");
////                buffer.append(cursor.getString(5)).append("\n");
//            }
//            //e1.setText(buffer.toString());
//
//            //new
//
//
//            list =new ArrayList<String>();
//            list.add(buffer.toString());
//
//
//
//            adapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,list);
//            listView.setAdapter(adapter);
//            e1.append("\n\n");
//
//
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                int i=0;
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    //e1.setText(query);
//                    //e1.setText("\n");
//
//                    if(db.checkname(query)==true) {
//                        e1.append(query + "\n");
//
//                        String s1[]=new String[100];
//                        int x=1;
//                        t1.append(x+"\n");
//
//
//                    }
//                    else
//                    {
//                        Toast.makeText(cartActivity.this,"not found",Toast.LENGTH_SHORT).show();
//                    }
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    adapter.getFilter().filter(newText);
//                    return false;
//                }
//            });
//
//
//
//        }
//
//
            e1=findViewById(R.id.getthenameforcart);
            e2=findViewById(R.id.getthequantityforcart);
            e3=findViewById(R.id.customernameforcart);
            b1=findViewById(R.id.confirmforaddtocart);
            b2=findViewById(R.id.showtocart);
            b3=findViewById(R.id.clearthecart);
            b4=findViewById(R.id.confermbill);


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    String s3=e3.getText().toString();




                    if (s1.equals("") || s2.equals("")||s3.equals(""))
                        Toast.makeText(cartActivity.this, "fields are empty", Toast.LENGTH_LONG).show();
                    else {
                        Boolean checkname = db.checkname(s1);
                        if (checkname == true) {
                            Cursor cursor = db.fromnamecheckquantity(s1,s2);
                            if (cursor.getCount()==0) {
                                Toast.makeText(cartActivity.this, "the quantity is above the limit", Toast.LENGTH_LONG).show();

                            } else {
                                Cursor cursor2=db.getcustomersbyname(s3);
                                if(cursor2.getCount()==0)
                                {
                                    Toast.makeText(cartActivity.this,"invalid user",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    while (cursor2.moveToNext()) {
                                        idforcus = String.valueOf(cursor2.getString(0));
                                        Toast.makeText(cartActivity.this, "id fro cust is " + idforcus, Toast.LENGTH_LONG).show();
                                    }


//
                                    while (cursor.moveToNext()) {

                                        String a = cursor.getString(0);

                                        String b = cursor.getString(1);
                                        //String c = cursor.getString(5);
                                        String c = idforcus;
                                        String d = s2;

                                        String e = cursor.getString(4);

                                        db.insertintoorders(a, b, c, d, e);
                                        db.insertintobill(a, c, b, d);
                                        Toast.makeText(cartActivity.this, "added to cart", Toast.LENGTH_LONG).show();


                                    }


                                    Cursor cursor1 = db.fromnamecheckquantity(s1, s2);
                                    while (cursor1.moveToNext()) {
                                        String zz = cursor1.getString(3);

                                        String yy = s2;
                                        int x = Integer.valueOf(zz);
                                        int y = Integer.valueOf(yy);
                                        int s = x - y;
                                        if (s <= 0) {
                                            s = 0;

                                        }

                                        String sz = String.valueOf(s);
                                        db.update(cursor1.getString(0), cursor1.getString(1), sz, cursor1.getString(4));
                                    }

                                    Cursor cursor11 = db.getsum();

                                    while (cursor11.moveToNext()) {
                                        x = Integer.valueOf(cursor11.getString(5));
                                    }
                                    sum = sum + x;
                                    x = 0;


                                }

                            }
                        } else
                            Toast.makeText(cartActivity.this, "Sorry the product name mentioned is not present", Toast.LENGTH_LONG).show();

                    }




                }
            });


            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cursor=db.showthecart();
                    if(cursor.getCount()==0)
                        Toast.makeText(cartActivity.this,"nothing found",Toast.LENGTH_LONG).show();
                    else
                    {
                        StringBuffer buffer=new StringBuffer();

                        while(cursor.moveToNext())
                        {
                            buffer.append(cursor.getColumnName(0)).append(" :").append(cursor.getString(0)).append("\n");
                            buffer.append(cursor.getColumnName(1)).append(" :").append(cursor.getString(1)).append("\n");
                            buffer.append("customer id").append(" :").append(cursor.getString(2)).append("\n");
                            buffer.append(cursor.getColumnName(3)).append(" :").append(cursor.getString(3)).append("\n");
                            buffer.append(cursor.getColumnName(4)).append(" :").append(cursor.getString(4)).append("\n");
                            buffer.append(cursor.getColumnName(5)).append(" :").append(cursor.getString(5)).append("\n");
                            buffer.append("........................................................\n");
                        }
                        t1.setMovementMethod(new ScrollingMovementMethod());

                        t1.setText(buffer.toString());
                        t2.setText(String.valueOf(sum));
                    }
                }
            });


            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.deletecartdetails();
                    db.deletebilldetails();
                    Toast.makeText(cartActivity.this,"cleared cart successfully",Toast.LENGTH_LONG).show();
                    t1.setText(null);
                    sum=0;
                    t2.setText(null);
                  //  db.setpreviousquantityinmed(id,quantity);
                }
            });

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!t1.getText().equals(""))
                    {
                        Intent intent=new Intent(cartActivity.this,billActivity.class);
                        String ki=String.valueOf(sum);
                        intent.putExtra("totalamount",ki);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(cartActivity.this,"Cart is empty",Toast.LENGTH_LONG).show();
                    }
                }
            });


    }
}
