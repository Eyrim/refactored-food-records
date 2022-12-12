package me.eyrim.foodrecords2;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("ingredient_name")
    private String ingredientName;
    @SerializedName("ingredient_drawable_tag")
    private int ingredientDrawableTag;

    public String getIngredientName() {
        return this.ingredientName;
    }

    public int getIngredientDrawableTag() {
        return this.ingredientDrawableTag;
    }
}
