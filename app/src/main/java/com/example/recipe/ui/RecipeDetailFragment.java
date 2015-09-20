package com.example.recipe.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.recipe.data.RecipeDescription;
import com.example.recipe.utility.Config;
import com.example.recipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {
    public static final String RECIPE_DETAIL_KEY = "RECIPE_DETAIL_KEY";
    View rootView;
    public static float MAX_CARD_HEIGHT_PECENTAGE = 0.35f;
    RecipeDescription mRecipeData;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_recipe2, null, false);
        mRecipeData = (RecipeDescription) getArguments()
                .getSerializable(RECIPE_DETAIL_KEY);
        ImageView recipeImage = (ImageView) rootView.findViewById(R.id.recipe_image);

        if (mRecipeData.getImageUrl() != null) {
            Picasso.with(recipeImage.getContext()).load(
                    mRecipeData.getImageUrl()).into(recipeImage);
        }
        setUpBannerSize(rootView);
        setUpTitle(rootView);
        setUpIngredientView(rootView);
        setUpDirection(rootView);
        setUpServesTxt(rootView);
        setUpPrepTimetxt(rootView);
        setUpNutritionView(rootView);
        return rootView;
    }

    public void setUpPrepTimetxt(View rootView) {
        TextView preparationTitle = (TextView) rootView.findViewById(R.id.preparation_title);
        preparationTitle.setText("Preparation Time");
        TextView preparationTime = (TextView) rootView.findViewById(R.id.preparationtime);
        preparationTime.setText(mRecipeData.getPreparationTime());
    }

    public void setUpServesTxt(View rootView) {
        TextView servesTitle = (TextView) rootView.findViewById(R.id.serves_title);
        servesTitle.setText("Serves");
        TextView serves = (TextView) rootView.findViewById(R.id.serves);
        serves.setText(mRecipeData.getServes());
    }

    public void setUpDirection(View rootView) {
        LinearLayout linearLayoutDirection = (LinearLayout) rootView.findViewById(R.id.direction_list);
        List<String> listDirection = mRecipeData.getDirections();
        for(String direction : listDirection){
            TextView tvDirection = new TextView(getActivity());
            tvDirection.setText(direction);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    linearLayoutDirection.getLayoutParams());
            params.setMargins(10, 10, 10, 10);
            linearLayoutDirection.addView(tvDirection, params);
        }
    }

    public void setUpNutritionView(View rootView) {
        LinearLayout linearLayoutDirection = (LinearLayout) rootView.findViewById(R.id.nutrition_list);
        List<String> listNutrition = mRecipeData.getNutritionList();

        if (listNutrition == null) {
            return;
        }

        for(String direction : listNutrition){
            TextView tvDirection = new TextView(getActivity());
            tvDirection.setText(direction);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    linearLayoutDirection.getLayoutParams());
            params.setMargins(10, 10, 10, 10);
            linearLayoutDirection.addView(tvDirection, params);
        }
    }

    public void setUpTitle(View rootView) {
        TextView recipe = (TextView)rootView.findViewById(R.id.recipeId);
        recipe.setText(mRecipeData.getTitle());
    }

    public void setUpBannerSize(View rootView) {
        RelativeLayout banner = (RelativeLayout) rootView.findViewById(R.id.banner);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = (int) (Config.SCREEN_SIZE.y
                * MAX_CARD_HEIGHT_PECENTAGE);
        banner.setLayoutParams(layoutParams);
        banner.requestLayout();
    }

    public void setUpIngredientView(View rootView){
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ingredient_list);
        List<String> list = mRecipeData.getIngredients();
        for (String ingredient : list) {
            TextView tv = new TextView(getActivity());
            tv.setText(ingredient);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    linearLayout.getLayoutParams());
            params.setMargins(5, 5, 5, 5);
            linearLayout.addView(tv, params);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
