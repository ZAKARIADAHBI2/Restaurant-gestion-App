package com.rajendra.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.foodapp.R;
import com.rajendra.foodapp.model.FesFood;

import java.util.List;


public class FesFoodAdapter extends RecyclerView.Adapter<FesFoodAdapter.AsiaFoodViewHolder> {

    Context context;
    List<FesFood> fesFoodList;



    public FesFoodAdapter(Context context, List<FesFood> fesFoodList) {
        this.context = context;
        this.fesFoodList = fesFoodList;
    }

    @NonNull
    @Override
    public AsiaFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fes_food_row_item, parent, false);
        return new AsiaFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AsiaFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(fesFoodList.get(position).getImageUrl());
        holder.name.setText(fesFoodList.get(position).getName());
        holder.price.setText(fesFoodList.get(position).getPrice());
        holder.rating.setText(fesFoodList.get(position).getRating());
        holder.restorantName.setText(fesFoodList.get(position).getRestorantname());

    }

    @Override
    public int getItemCount() {
        return fesFoodList.size();
    }


    public static final class AsiaFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public AsiaFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}
