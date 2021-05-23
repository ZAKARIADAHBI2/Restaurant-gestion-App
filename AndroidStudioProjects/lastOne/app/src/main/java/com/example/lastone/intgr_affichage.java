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
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class intgr_affichage  extends AppCompatActivity {
    private EditText txt1;
    private EditText txt2;
    private EditText txt3;
    private Spinner spinner;
        Controllerdb controllerdb = new Controllerdb(this);
        SQLiteDatabase db;

   // String textspnr = spinner.getSelectedItem().toString();
        private ArrayList<String> Id = new ArrayList<String>();
        private ArrayList<String> Name = new ArrayList<String>();
        private ArrayList<String> Amout = new ArrayList<String>();
        private ArrayList<String> Unit = new ArrayList<String>();
        private ArrayList<String> price = new ArrayList<String>();
        ListView lv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.affichage);
            lv = (ListView) findViewById(R.id.listvw);
            txt1 =findViewById(R.id.txt1);
            txt2=findViewById(R.id.txt2);
            txt3=findViewById(R.id.txt3);
            spinner= findViewById(R.id.spinneringr);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent editScreenIntent = new Intent(intgr_affichage.this, ingrdiants.class);

                    startActivity(editScreenIntent);

                }
            });
}

        @Override
        protected void onResume() {
            displayData();
            super.onResume();
        }
        private void displayData() {
            db = controllerdb.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM  ingrdiants",null);
            Id.clear();
            Name.clear();
            Amout.clear();
            Unit.clear();
            price.clear();
            if (cursor.moveToFirst()) {
                do {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    Name.add(cursor.getString(cursor.getColumnIndex("Name")));
                    Amout.add(cursor.getString(cursor.getColumnIndex("Amout")));
                    Unit.add(cursor.getString(cursor.getColumnIndex("Unit")));
                    price.add(cursor.getString(cursor.getColumnIndex("price")));
                } while (cursor.moveToNext());
            }
            CustomAdapter ca = new CustomAdapter(intgr_affichage.this,Id, Name,Amout,Unit,price);
            lv.setAdapter(ca);
            //code to set adapter to populate list
            cursor.close();
        }

    }
