package com.example.kharaaaaa.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.print.PageRange;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kharaaaaa.R;
import com.example.kharaaaaa.models.Ingredient;
import com.example.kharaaaaa.models.Recipe;
import com.example.kharaaaaa.ui.fragments.RecipeIngredientsFragment;

import java.io.File;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private Context mContext;
    private RecipeListener recipeListener;
    private double mtotal=0;
    private List<Ingredient> ingredientList;

   RecipeIngredientsFragment recipeIngredientsFragment;
    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        mContext = context;
        this.recipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_row, parent, false);
        return new RecipeViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView titleLabel;
        TextView titleLabel2;
        ImageView thumbnail;
        ImageView overflow;

        public RecipeViewHolder(View itemView) {
            super(itemView);

            titleLabel = itemView.findViewById(R.id.titleLabel);
            titleLabel2 =itemView.findViewById(R.id.titleLabel2money);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            overflow = itemView.findViewById(R.id.overflow);

            itemView.setOnClickListener(v -> {
                if (recipeListener != null)
                    recipeListener.onShowRecipe(recipeList.get(getAdapterPosition()), new Pair[]{
                            Pair.create(thumbnail, "image_shared")
                    });
            });
            overflow.setOnClickListener(this::showPopupMenu);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Recipe recipe) {
            titleLabel.setText(recipe.getName());
            mtotal = recipe.getIngredients().stream().mapToDouble(i->i.grantotal()).sum();

            titleLabel2.setText(String.valueOf(mtotal));//String.valueOf(mtotal)
        thumbnail.setImageURI(Uri.fromFile(new File(recipe.getImagePath())));
        }

        private void showPopupMenu(View view) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.inflate(R.menu.gride);
            //androidx.activity.
            popup.setOnMenuItemClickListener(
                    new MyMenuItemClickListener(recipeList.get(getAdapterPosition())));
            popup.show();
        }
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            private Recipe recipe;

            public MyMenuItemClickListener(Recipe recipe) {
                this.recipe = recipe;
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_edit_recipe:
                        if (recipeListener != null)
                            recipeListener.onEditRecipe(recipe);
                        break;
                    case R.id.action_delete_recipe:
                        if (recipeListener != null)
                            recipeListener.onDeleteRecipe(recipe.getId());
                        return true;
                    default:
                }
                return false;
            }
        }
    }

    public void setRecipeListener(RecipeListener recipeListener) {
        this.recipeListener = recipeListener;
    }

    public interface RecipeListener {
        void onShowRecipe(Recipe recipe, Pair<View, String>[] pairs);
        void onEditRecipe(Recipe recipe);
        void onDeleteRecipe(long recipeId);
    }
}
