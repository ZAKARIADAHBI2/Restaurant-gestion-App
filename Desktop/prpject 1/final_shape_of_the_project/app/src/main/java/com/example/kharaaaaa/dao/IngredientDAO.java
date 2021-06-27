package com.example.kharaaaaa.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kharaaaaa.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zakaria dahbi
 */
public class IngredientDAO {

    private SQLiteDatabase db;

    public IngredientDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Ingredient ingredient) {
        insert(ingredient.getName(), ingredient.getRecipeId(), ingredient.getAmout() ,ingredient.getPrice() , ingredient.getUnit());
    }

    public void insert(String ingredient, long recipeId, double Amout , double price , String Unit) {
        ContentValues values = new ContentValues();
        values.put(Config.KEY_NAME, ingredient);
        values.put(Config.KEY_RECIPE_ID, recipeId);
        values.put(Config.KEY_AMOUT, Amout);
        values.put(Config.KEY_PRICE, price);
        values.put(Config.KEY_UNIT, Unit);
        db.insert(Config.TABLE_NAME, null, values);
    }

    public List<Ingredient> selectAllByRecipeId(long recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Cursor cursor = db.query(Config.TABLE_NAME,
                new String[]{Config.KEY_ID, Config.KEY_NAME,Config.KEY_AMOUT, Config.KEY_PRICE,Config.KEY_UNIT, Config.KEY_RECIPE_ID},
                Config.KEY_RECIPE_ID + " = ?", new String[]{recipeId + ""}, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    ingredients.add(new Ingredient(
                            cursor.getLong(0), cursor.getString(1),cursor.getDouble(2),cursor.getDouble(3),cursor.getString(4) ,cursor.getLong(5)));
                } while (cursor.moveToNext());
            }
        }
        //error here need to fetch data

        Log.i("DAO", "IngredientDAO returning: " + ingredients + " for recipe ID: " + recipeId);
        return ingredients;
    }
    public boolean deleteAllByRecipeId(long recipeId) {
        return db.delete(Config.TABLE_NAME, Config.KEY_RECIPE_ID + "=" + recipeId, null) > 0;
    }
    public static class Config {
        public static final String TABLE_NAME = "ingrediantss";
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_AMOUT = "Amout";
        public static final String KEY_PRICE = "price";
        public static final String KEY_UNIT = "Unit";
        public static final String KEY_RECIPE_ID = "recipeId";
        public static final String CREATE_TABLE_STATEMENT =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_NAME + " TEXT NOT NULL, " +
                        KEY_AMOUT + " TEXT NOT NULL, " +
                        KEY_PRICE + " TEXT NOT NULL, " +
                        KEY_UNIT + " TEXT NOT NULL, " +
                        KEY_RECIPE_ID + " TEXT NOT NULL, " +
                        "FOREIGN KEY(" + KEY_RECIPE_ID + ") REFERENCES " + RecipeDAO.Config.TABLE_NAME + "(" + RecipeDAO.Config.KEY_ID + "))";
    }
}
