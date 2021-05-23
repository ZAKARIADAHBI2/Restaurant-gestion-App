package com.example.lastone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  private  Button btn1;
  private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.recbtn);
        btn2 = findViewById(R.id.ingbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_recipe();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_ingr();
            }
        });

    }
    public void openNewActivity_recipe(){
        Intent intent = new Intent(this, ingrdiants.class);
        startActivity(intent);
    }
    public void openNewActivity_ingr(){
        Intent intent = new Intent(this, add_recipe.class);
        startActivity(intent);
    }
}