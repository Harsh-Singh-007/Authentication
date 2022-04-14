package com.harsh.authentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (email TEXT primary key, name TEXT , mobile TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public boolean insertData(String name, String email,String number, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("mobile", number);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1) {
            return false;
        } else {
            return true;
        }
    }

    public  Boolean checkemail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public  Boolean checknumber(String mobile){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mobile = ?", new String[]{mobile});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public  Boolean checkemailpassword(String email,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{email,password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public  Boolean checknumberpassword(String number,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mobile = ? and password = ?", new String[]{number,password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}

