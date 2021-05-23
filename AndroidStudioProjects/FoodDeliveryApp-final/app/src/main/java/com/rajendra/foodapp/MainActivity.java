package com.rajendra.foodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.foodapp.adapter.FesFoodAdapter;
import com.rajendra.foodapp.adapter.PopularFoodAdapter;
import com.rajendra.foodapp.model.FesFood;
import com.rajendra.foodapp.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    FesFoodAdapter fesFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // now here we will add some dummy data to out model class

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Float Cake ", "$7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "$17.05", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish  Stick", "$25.05", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Float Cake ", "$7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken ", "$17.05", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish  Stick", "$25.05", R.drawable.popularfood3));

        setPopularRecycler(popularFoodList);


        List<FesFood> fesFoodList = new ArrayList<>();
        fesFoodList.add(new FesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        fesFoodList.add(new FesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        fesFoodList.add(new FesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        fesFoodList.add(new FesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        fesFoodList.add(new FesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        fesFoodList.add(new FesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));

        setAsiaRecycler(fesFoodList);

    }


    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<FesFood> fesFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        fesFoodAdapter = new FesFoodAdapter(this, fesFoodList);
        asiaRecycler.setAdapter(fesFoodAdapter);

    }


}
