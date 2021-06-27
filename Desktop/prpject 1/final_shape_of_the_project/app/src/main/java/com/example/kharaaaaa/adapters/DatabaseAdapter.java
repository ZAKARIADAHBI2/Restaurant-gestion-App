package com.example.kharaaaaa.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.kharaaaaa.dao.RecipeDAO;
import com.example.kharaaaaa.dao.SQLiteDatabaseHelper;
import com.example.kharaaaaa.dao.UserDAO;
import com.example.kharaaaaa.models.Recipe;
import com.example.kharaaaaa.models.User;
import com.example.kharaaaaa.utils.UserPreferences;

import java.util.List;

public class DatabaseAdapter {
    /**
     * The singleton instance.
     */
    private static DatabaseAdapter instance;
    private static final String DATABASE_NAME = "recipesDatabasetwo";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context mContext;

    private UserDAO userDAO;
    private RecipeDAO recipeDAO;

    /**
     * A static factory method.
     *
     * @param context
     * @return the singleton instance of the DatabaseAdapter.
     */
    public static DatabaseAdapter getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseAdapter.class) {
                if (instance == null)
                    instance = new DatabaseAdapter(context).open();
            }
        }

        return instance;
    }

    private DatabaseAdapter(Context context) {
        mContext = context;
        dbHelper = new SQLiteDatabaseHelper(context, DATABASE_NAME, DATABASE_VERSION);
    }

    private DatabaseAdapter open() {
        db = dbHelper.getWritableDatabase();
        userDAO = new UserDAO(db);
        recipeDAO = new RecipeDAO(db);
        return this;
    }

    public boolean signIn(String email, String password) {
        User currentUser = userDAO.getUserByEmailAndPassword(email, password);
        UserPreferences.saveCurrentUser(mContext, currentUser);
        return currentUser != null;
    }

    public void addNewUser(User user) {
        userDAO.insert(user);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long addNewRecipe(Recipe recipe) {
        return recipeDAO.insert(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipeDAO.update(recipe);
    }

    public void deleteRecipe(long recipeId) {
        recipeDAO.deleteById(recipeId);
    }

    public List<Recipe> getAllRecipesByCategory(String category) {
        return recipeDAO.selectAllByCategory(category);
    }
}
