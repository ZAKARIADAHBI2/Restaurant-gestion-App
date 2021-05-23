package com.example.lastone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class recip_affichage  extends AppCompatActivity {
    private static final String TAG = "recip_affichge";
    Controllerdbs controllerdbs = new Controllerdbs(this);
    SQLiteDatabase dbb;
    private EditText txt1;
    private ListView mListView;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutrecip);
        mListView = (ListView) findViewById(R.id.listView);
        txt1= findViewById(R.id.recipname);


    }


    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        dbb = controllerdbs.getReadableDatabase();
        Cursor cursor = dbb.rawQuery("SELECT * FROM  recipes",null);
        Id.clear();
        Name.clear();

        if (cursor.moveToFirst()) {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                Name.add(cursor.getString(cursor.getColumnIndex("Name")));

            } while (cursor.moveToNext());
        }
        CustomAd ca = new CustomAd(recip_affichage.this,Id, Name);
        mListView.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }


    /**
     * customizable toast
     * @param message
     */

}
