package com.example.lastone;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.jar.Attributes;

public class ingrdiants extends AppCompatActivity {

    Controllerdb db =new Controllerdb(this);
    SQLiteDatabase database;
    private EditText txt1;
    private EditText txt2;
    private EditText txt3;
    private Spinner spinner;
    private Button btnsave;
    private Button btncancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrediants_act);
        txt1 =findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        spinner= findViewById(R.id.spinneringr);
        String textspnr = spinner.getSelectedItem().toString();
        btnsave= findViewById(R.id.savebtn);
        btncancel= findViewById(R.id.cancelbtn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=db.getWritableDatabase();
                database.execSQL("INSERT INTO ingrdiants(Name,Amout,Unit,price)VALUES('"+ txt1.getText()+"','"+txt2.getText()+"','"+textspnr+"','"+txt3.getText()+"')" );
                Toast.makeText(ingrdiants.this,"Data Inserted To Sqlite Database",Toast.LENGTH_LONG).show();
            }

        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAfichge();
            }
        });
    }
    public void openAfichge(){
        Intent intent = new Intent(this, intgr_affichage.class);
        startActivity(intent);
    }
}
