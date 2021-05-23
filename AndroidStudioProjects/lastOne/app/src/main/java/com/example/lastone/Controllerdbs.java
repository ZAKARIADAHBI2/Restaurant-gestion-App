package com.example.lastone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Controllerdbs extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="SqliteListviewDB";
    public Controllerdbs(Context applicationcontext) {
        super(applicationcontext, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryy;
        queryy = "CREATE TABLE IF NOT EXISTS recipes(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR );";
        db.execSQL(queryy);
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM recipes ";
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
        query = "DROP TABLE IF EXISTS recipes";
        db.execSQL(query);
        onCreate(db);

    }
}
