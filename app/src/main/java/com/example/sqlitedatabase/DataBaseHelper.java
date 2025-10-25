package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_Name="my_database";
    public static final int DB_Version=1;
    public DataBaseHelper(Context context) {
        super(context,DB_Name,null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table my_table(id INTEGER primary key autoincrement,name TEXT,number TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists my_table");

    }
    public void insertData(String name,String number){
        SQLiteDatabase database=getWritableDatabase();

        ContentValues conval=new ContentValues();
        conval.put("name",name);
        conval.put("number",number);
        database.insert("my_table",null,conval);

    }

    public Cursor getAllData(){   //data dekhay
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from my_table",null);
        return cursor;
    }

    public Cursor searchDatabyID(int id){ //data search korte
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from my_table where id like'"+id+"' ",null);
        return cursor;
    }

    public Cursor searchDatabyName(String name){ //data search korte
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from my_table where name like'%"+name+"%' ",null);  //similardata pate % bebohar korbo
        return cursor;
    }
}
