package com.example.lastone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shree on 10/22/2016.
 */
class Controllerdb extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="SqliteListviewDB";
    public Controllerdb(Context applicationcontext) {
        super(applicationcontext, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS ingrdiants(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR,Amout VARCHAR,Unit VARCHAR,price VARCHAR);";
        db.execSQL(query);

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM ingrdiants ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

//    public Cursor getItemID(String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT Id FROM ingrdiants WHERE Name = '" + name + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ;
        query = "DROP TABLE IF EXISTS ingrdiants";
        db.execSQL(query);
        onCreate(db);
    }



}