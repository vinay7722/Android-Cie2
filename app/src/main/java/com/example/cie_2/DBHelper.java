package com.example.cie_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.cie_2.FuelItemEntry;


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context,"database.db",null,1);
    }

    //this method is use for create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table driverdetail(fname Text, lname Text, uname Text, password Text)");
        db.execSQL("create table FuelEntry(userName Text, date Text, volume REAL, rupeesPerLitre Text, previousOdometerReading REAL, currentOdometerReading REAL, average REAL)");
    }

    //this method is use for Drop A table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists driverdetail");
        db.execSQL("drop table if exists FuelEntry");
    }

    //this method is use for Insert Driver Personal Details
    public boolean insertDriverData(String fname,String lname,String uname,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("lname",lname);
        contentValues.put("uname",uname);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert("driverdetail",null,contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //this method is use for Check username when we register driver details
    public boolean checkusername(String username)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from driverdetail where uname = ?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //this method is use for Check username And password when login
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from driverdetail where uname = ? and password = ?",new String[]{username,password});
        System.out.println(cursor.getCount());
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }

    //this method is use for Insert Data of Fuel
    public Boolean insertDriverEntry(String username, String date, double volume, String rsperlt, double previousOdometerReading, double currodread, double avg) {
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("userName",username);
            contentValues.put("date",date);
            contentValues.put("volume",volume);
            contentValues.put("rupeesPerLitre",rsperlt);
            contentValues.put("previousOdometerReading",previousOdometerReading);
            contentValues.put("currentOdometerReading",currodread);
            contentValues.put("average",avg);

            long result = sqLiteDatabase.insert("FuelEntry",null,contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return false;
    }



    public ArrayList<FuelItemEntry> driverFetchEntry(String userName){
        ArrayList<FuelItemEntry> list = new ArrayList<FuelItemEntry>();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from FuelEntry where userName = ?", new String[]{userName});  //where userName = ?
            System.out.println(" cursor " + cursor.getCount());

            if(cursor.moveToFirst()){
                do {
                    list.add(new FuelItemEntry(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                            cursor.getString(4), cursor.getString(5), cursor.getString(6)));
//                    System.out.println(list.get(1));
                } while (cursor.moveToNext());
            }else{
                System.out.println("else ni andar");
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println(list.size());
        return list;
    }
}
