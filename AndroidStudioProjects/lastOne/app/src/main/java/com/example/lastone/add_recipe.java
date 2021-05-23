package com.example.lastone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class add_recipe extends AppCompatActivity {
    //my parameters
    //there is a preblem when i try to add recip
    private EditText txt1;
    private Spinner spinner2;
    private Button btns;
    private Button btnc;
    private ListView listv;
    private  EditText recipname;

    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase dbb;
    SQLiteDatabase db;
    Controllerdbs controllerdbs= new Controllerdbs(this);

    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Amout = new ArrayList<String>();
    private ArrayList<String> Unit = new ArrayList<String>();
    private ArrayList<String> price = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_add);
        txt1=findViewById(R.id.txtamount);
        spinner2= findViewById(R.id.spinner2);
        btns= findViewById(R.id.btns);
        btnc= findViewById(R.id.btnc);
        listv = findViewById(R.id.listv2);
        recipname = findViewById(R.id.recipname);

btns.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dbb=controllerdbs.getWritableDatabase();
        dbb.execSQL("INSERT INTO recipes(Name)VALUES('"+ recipname.getText()+"')" );
        Toast.makeText(add_recipe.this,"Data Inserted To Sqlite Database",Toast.LENGTH_LONG).show();

    }
});
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_recipe.this, recip_affichage.class);
                startActivity(intent);
            }
        });
//        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
//                String selectedFromList =(String) (listv.getItemAtPosition(myItemInt));
//
//            }
//        });
    }

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
        CustomAdapter ca = new CustomAdapter(add_recipe.this,Id, Name,Amout,Unit,price);
        listv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }




   }
