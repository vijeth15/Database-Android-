package com.example.databaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class DatabaseHelper1 extends SQLiteOpenHelper {


    public DatabaseHelper1( Context context) {
        super(context, "medic.db", null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
       // if (db.isReadOnly())
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists company(companyid text primary key,companyname text,companyemail text,companylocation text)");

        db.execSQL("create table if not exists medicine(productid text primary key,productname text,category text, quantity text ,price text,anytext text references company(companyid) on delete cascade)");
        db.execSQL("create table if not exists cartidquantity(prodid text,quan text)");

        db.execSQL("create table if not exists customers(customerid integer primary key autoincrement,customername text,customerphone text,customerlocation text )");
        db.execSQL("create table if not exists deletedlogs(pid text,pname text,cat text,qua text,pri text,cid text)");
        db.execSQL("create table if not exists orders(productid text ,productname text,companyid text,quantity text,cost text,totalcost text,foreign key(productid) references medicine(productid) on delete cascade,foreign key(companyid) references customers(customerid) on delete cascade)");
        db.execSQL("create trigger if not exists t1 after insert on orders for each row begin update orders set totalcost = new.cost*new.quantity where productid=new.productid;end;");
        db.execSQL("create table if not exists bill(transationid integer primary key autoincrement,productid text,companyid text,productname text,quantity text)");
        db.execSQL("create view view1 as select *from bill;");

       // db.execSQL("create trigger if not exists t2 after insert on medicine for each row begin update medicine set quantity=new.quantity-orders.quantity where productid=new.productid;end;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists medicine");
        db.execSQL("drop table if exists cartidquantity");
        db.execSQL("drop table if exists customers");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists bill");
    }





    //inserting to the table medicine;
    public boolean insert(String productid,String productname,String category,String quantity,String price,String anytext)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("productid",productid);
        contentValues.put("productname",productname);
        contentValues.put("category",category);
        contentValues.put("quantity",quantity);
        contentValues.put("price",price);
        contentValues.put("anytext",anytext);

        long ins=db.insert("medicine",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    public Cursor getfullproddetails(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from medicine",null);
        return cursor;

    }

    public Integer delete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("medicine","productid=?",new String[]{id});

    }

    public  boolean checkid(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from medicine where productid=?",new String[]{id});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public  boolean update(String id,String name, String quantity,String cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("productid",id);
        contentValues.put("productname",name);
        contentValues.put("quantity",quantity);
        contentValues.put("price",cost);
        db.update("medicine",contentValues,"productid=?",new String[]{id});
        return true;
    }






    public boolean idandquantity(String id, String quantity)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues1=new ContentValues();
        contentValues1.put("prodid",id);
        contentValues1.put("quan",quantity);
        long ins=db.insert("cartidquantity",null,contentValues1);
        //cartidquantity

        if(ins==-1)
            return false;
        else
            return true;
    }

    public Cursor getCartData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //  Cursor cursor= db.rawQuery("select *from ")
        Cursor cursor=db.rawQuery("select *from medicine inner join cartidquantity on medicine.productid=cartidquantity.prodid",null);
        //Cursor cursor=db.rawQuery("select *from medicine",null);
        return cursor;

    }

    public Integer delete()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("cartidquantity","prodid IN(select productid from medicine)",null);
    }

    public boolean insertintoCustomers(String id,String name ,String phonenumber ,String location)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("customername",name);
        contentValues.put("customerphone",phonenumber);
        contentValues.put("customerlocation",location);

    long ins=db.insert("customers",null,contentValues);
    if (ins==-1)
        return false;
    else
        return true;

    }

    public boolean checkname(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from medicine where productname=?",new String[]{name});
        if (cursor.getCount()>0)
        return true;
        else
            return false;
    }

    public Cursor displaycustomers()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from customers",null);
        return cursor;
    }

    public int countofcustomers()
    {
        int count=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select count(customerid) from customers",null);
        if(cursor.getCount()>0)
        {
            cursor.moveToNext();
            count=cursor.getInt(0);
        }

        return count;

    }
    public boolean insertintocompany(String companyid,String companyname,String companyemail,String location)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("companyid",companyid);
        contentValues.put("companyname",companyname);
        contentValues.put("companyemail",companyemail);
        contentValues.put("companylocation",location);

        long ins=db.insert("company",null,contentValues);

        if(ins==-1)
            return  false;
        else
            return true;
    }

    public Cursor displaycompany()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from company",null);
        return cursor;
    }

    public Cursor setlog(String s1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("insert into deletedlogs select *from medicine where productid=?",new String[]{s1});
        return cursor;

    }

    public void insertintologs(String s1,String s2,String s3,String s4,String s5,String s6)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("pid",s1);
        contentValues.put("pname",s2);
        contentValues.put("cat",s3);
        contentValues.put("qua",s4);
        contentValues.put("pri",s5);
        contentValues.put("cid",s6);
        db.insert("deletedlogs",null,contentValues);
        //pid text,pname text,cat text,qua text,pri text,cid text
    }

    public Cursor getlogs()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from deletedlogs",null);
        return cursor;

    }

    public  Cursor fromnamecheckquantity(String name,String quantity)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from medicine where productname=? and quantity>=?",new String[]{name,quantity});
        return cursor;
    }

    public boolean insertintoorders(String pid,String pname,String cid,String quantity,String cost )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("productid",pid);
        contentValues.put("productname",pname);
        contentValues.put("companyid",cid);
        contentValues.put("quantity",quantity);
        contentValues.put("cost",cost);
       long ins=db.insert("orders",null,contentValues);
        // db.execSQL("create table if not exists orders(productid text ,productname text,companyid text,quantity text,cost text,foreign key(productid) references medicine(productid),foreign key(companyid) references company(companyid))");
        if (ins==-1)
            return false;
        else
        return true;
    }


    public Cursor showthecart()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from orders",null);
        return cursor;
    }

    public  void deletecartdetails()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from orders");
    }

    public Cursor getproductfromcompany(String s1)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select productid ,productname from medicine where anytext=?",new String[]{s1});
        return cursor;
    }

    public Cursor checkcompid(String s1)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from company where companyid=?",new String[]{s1});
        return cursor;

    }

    public boolean updatethecompany(String s1,String s2,String s3,String s4)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("companyid",s1);
        contentValues.put("companyname",s2);
        contentValues.put("companyemail",s3);
        contentValues.put("companylocation",s4);
        //db.execSQL("create table if not exists company(companyid text primary key,companyname text,companyemail text,companylocation text)");
        db.update("company",contentValues,"companyid=?",new String[]{s1});
        return true;

    }


    public Integer deletecomp(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("company","companyid=?",new String[]{id});

    }


    public  Cursor getqu(String s1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select quantity from medicine where productname=?",new String[]{s1});
        return cursor;
    }
//public Cursor getquantityfrommedicine(String name)
//{
//    SQLiteDatabase db=this.getReadableDatabase();
//    Cursor cursor=db.rawQuery("select *from medicine where productname=?",new String[]{name});
//    return cursor;
//}
//
////public Cursor getquantityfromorders(String name)
////    {
////        SQLiteDatabase db=this.getReadableDatabase();
////        Cursor cursor=db.rawQuery("select *from orders where productname=?",new String[]{name});
////        return cursor;
////    }
//
//    public  boolean updatemedincinevianame(String name,String quantity)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("productname",name);
//        contentValues.put("quantity",quantity);
//        db.update("medicine",contentValues,"where productname=?",new String[]{name});
//        return true;
//
//    }

    public  boolean updatename(String name,String q)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("quantity",q);
        db.update("medicine",contentValues,"where productname=?",new String[] {name});
        return true;
    }

    public  Cursor getsum()
    {
        SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor=db.rawQuery("select *from orders",null);
       return cursor;

    }

    public void insertintobill(String productid,String companyid ,String productname,String quantity )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        // db.execSQL("create table if not exists bill(transationid text primary key autoincrement,productid text,companyid text,productname text,quantity text)");
        contentValues.put("productid",productid);
        contentValues.put("companyid",companyid);
        contentValues.put("productname",productname);
        contentValues.put("quantity",quantity);
        db.insert("bill",null,contentValues);
    }

    public  void deletebilldetails()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from bill");
    }

    public  Cursor getdatafrombill()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from bill",null);
        return cursor;
    }

//    public void setpreviousquantityinmed(String id,String q)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("quantity",q);
//        db.update("medicine",contentValues,"where productid=?",new String[] {id});
//
//    }

    public Cursor getcustomersbyname(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from customers where customername=?",new String[]{name});
        return cursor;
    }

    public Cursor getview()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from view1",null);
        return cursor;
    }

    public Cursor checkidfromlog(String s1)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from deletedlogs where pid=?",new String[]{s1});
        return cursor;
    }

    public Cursor setmedicine(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("insert into medicine select *from deletedlogs where pid=?",new String[]{id});
        return cursor;

    }

    public  Cursor deletelog(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor1=db.rawQuery("delete from deletedlogs where pid=?",new String[]{id});
        return cursor1;

    }

    public  Cursor checkidfromcust(String s1)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from customers where customerid=?",new String[]{s1});
        return  cursor;
    }

    public Cursor dispthecustprod(String s1)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select productid ,productname from orders where companyid=?",new String[]{s1});
        return cursor;
    }
}

