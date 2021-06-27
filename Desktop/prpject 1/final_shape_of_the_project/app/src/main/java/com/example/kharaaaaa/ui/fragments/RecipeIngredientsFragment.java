package com.example.kharaaaaa.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kharaaaaa.R;
import com.example.kharaaaaa.adapters.DatabaseAdapter;
import com.example.kharaaaaa.adapters.IngredientAdapter;
import com.example.kharaaaaa.models.Ingredient;
import com.example.kharaaaaa.models.Recipe;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeIngredientsFragment extends NavigableFragment {

    private IngredientListener mListener;
    private List<Ingredient> ingredientList;
    private IngredientAdapter ingredientAdapter;
    private DatabaseAdapter databaseAdapter;
    private RecyclerView ingredientRecyclerView;
    private TextView emptyView;
    private Button addButton;
    private EditText ingredientField;
    private EditText amountt;
    private EditText pricee;
    private Spinner unitt;
    public RecipeIngredientsFragment() {

    }

    public static RecipeIngredientsFragment newInstance(Recipe recipe) {
        RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();

        if (recipe.getIngredients() != null) {
            Bundle args = new Bundle();
            args.putParcelableArrayList("ingrediantss", (ArrayList<Ingredient>) recipe.getIngredients());
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);
        databaseAdapter = DatabaseAdapter.getInstance(getActivity());

        Bundle args = getArguments();
        if (args != null)
            ingredientList = args.getParcelableArrayList("ingrediantss");
        if (ingredientList == null)
            ingredientList = new ArrayList<>();

        ingredientRecyclerView = view.findViewById(R.id.recyclerView);
        emptyView = view.findViewById(R.id.empty_view);
        //emptyView2= view.findViewById(R.id.empty_view2);
        addButton = view.findViewById(R.id.add_button);
        ingredientField = view.findViewById(R.id.ingredientField);
        amountt =  view.findViewById(R.id.amountt);
        pricee =  view.findViewById(R.id.pricee);
        unitt =  view.findViewById(R.id.unitt);
        ingredientAdapter = new IngredientAdapter(getActivity(), ingredientList );
        ingredientAdapter.setIngredientListener(position -> {
            ingredientList.remove(position);
            toggleEmptyView();
            ingredientAdapter.notifyDataSetChanged();
        });

        toggleEmptyView();

        ingredientRecyclerView.setHasFixedSize(true);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        addButton.setOnClickListener(v -> {
            String newIngredient = ingredientField.getText().toString();
             double newAmount =Integer.parseInt(amountt.getText().toString()) ;
             double newPrice = Integer.parseInt(pricee.getText().toString());
            String newUnit = unitt.getSelectedItem().toString();
            if (!newIngredient.isEmpty() ) {
                ingredientField.setText("");
                amountt.setText("");
                pricee.setText("");

                ingredientList.add(new Ingredient(newIngredient,newAmount,newPrice,newUnit));
                toggleEmptyView();
                ingredientAdapter.notifyDataSetChanged();
            }

        });

        return view;
    }

    private void toggleEmptyView() {
        if (ingredientList.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);


            ingredientRecyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            ingredientRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (IngredientListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement IngredientListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onNext() {
        if (mListener != null)
            mListener.navigateToDirectionsFragment(ingredientList);
    }

    public interface IngredientListener {
        void navigateToDirectionsFragment(List<Ingredient> ingredients);
    }
}
