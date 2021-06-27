package com.example.kharaaaaa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kharaaaaa.R;
import com.example.kharaaaaa.models.Ingredient;

import java.util.List;

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;
    private boolean isEditable = true;
    private Context mContext;
    private IngredientListener ingredientListener;
    int totalPrice = 0;
    public IngredientAdapter(Context context, List<Ingredient> ingredientList ) {
        mContext = context;
        this.ingredientList = ingredientList;
//         this.x=x;
//        for (int i = 0; i < ingredientList.size(); i++) {
//
//         x = x + (ingredientList.get(i).getPrice().toString());
//            //here only I added all prices before changing qty..
//
//        }

    }

    //ingrdiant price get totale

    public IngredientAdapter(Context context, List<Ingredient> ingredientList, boolean isEditable) {
        mContext = context;
        this.ingredientList = ingredientList;
        this.isEditable = isEditable;
    }

    @Override
    public int getItemViewType(int position) {
        return isEditable ? 0 : 1;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewType == 0 ? R.layout.ingredient_item_row : R.layout.ingredient_item_row_non_editable,
                        parent, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient);
        //grandTotal();
    }
//    public int grandTotal(){
//
//        int totalPrice = 0;
//        for(int i = 0 ; i < ingredientList.size(); i++) {
//            totalPrice += ingredientList.get(i).getPrice();
//        }
//
//        return totalPrice;
//    }
    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

//    public int grandTotal( ) {
//
//
//        for(int i = 0 ; i < ingredientList.size(); i++) {
//            totalPrice += ingredientList.get(i).getPrice();
//        }
//
//        return totalPrice;
//    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientText;
        TextView ingredientText2;
        ImageView wasteBin;
        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientText = itemView.findViewById(R.id.ingredientText);
            ingredientText2 = itemView.findViewById(R.id.ingredientText2);
            if (isEditable) {
                wasteBin = itemView.findViewById(R.id.wasteBin);
                wasteBin.setOnClickListener(v -> {
                    if (ingredientListener != null)
                        ingredientListener.onDeleteIngredient(getAdapterPosition());

                });
            }
        }

//
//        public String grandTotal(List<Ingredient> ingredientList){
//
//            String totalPrice = "0";
//
//            for(int i = 0 ; i < ingredientList.size(); i++) {
//                totalPrice += ingredientList.get(i).getPrice();
//            }
//
//            return totalPrice;
//        }
        public void bind(Ingredient ingredient) {
            ingredientText.setText(ingredient.getName());
//            ingredientText.setText(ingredient.getAmout());
            ingredientText2.setText(String.valueOf(ingredient.getPrice()));
//            ingredientText.setText(ingredient.getUnit());



        }
    }

    public void setIngredientListener(IngredientListener ingredientListener) {
        this.ingredientListener = ingredientListener;
    }

    public interface IngredientListener {
        void onDeleteIngredient(int position);
    }
}
