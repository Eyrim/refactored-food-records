package me.eyrim.foodrecords2;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("recipe_name")
    private String recipeName;
    @SerializedName("recipe_desc")
    private String recipeDesc;
    @SerializedName("ingredients")
    private Ingredient[] ingredients; // TODO: 24/10/2022 Make this a data class itself, and allow caching so the user doesn't have to input the image and stuff every time
    @SerializedName("recipe_id")
    private String recipeId;

    public String getRecipeName() {
        return this.recipeName;
    }

    public String getRecipeDesc() {
        return this.recipeDesc;
    }

    public Ingredient[] getIngredients() {
        return this.ingredients;
    }

    public String getRecipeId() {
        return this.recipeId;
    }
}

