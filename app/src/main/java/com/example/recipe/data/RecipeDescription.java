package com.example.recipe.data;

import com.example.recipe.utility.Util;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 13/9/15.
 */
public class RecipeDescription implements Serializable{


    public String title;
    public String imageUrl;
    public List<String> ingredients;
    public List<String> directions;
    public String serves;
    public String preparationTime;
    public List<String> nutritionList;

    public static RecipeDescription getRecipeDescription() {
        String json = Util.jsonData;
        Gson gson = new Gson();
        RecipeDescription desc = gson.fromJson(json, RecipeDescription.class);
        return desc;
    }

    public String getTitle() {
        return title;
    }



    public String getImageUrl() {
        return imageUrl;
    }



    public List<String> getIngredients() {
        return ingredients;
    }



    public List<String> getDirections() {
        return directions;
    }



    public String getServes() {
        return serves;
    }


    public String getPreparationTime() {
        return preparationTime;
    }

    public List<String> getNutritionList() {
        return nutritionList;
    }

    public void setNutritionList(List<String> nutritionList) {
        this.nutritionList = nutritionList;
    }


}
