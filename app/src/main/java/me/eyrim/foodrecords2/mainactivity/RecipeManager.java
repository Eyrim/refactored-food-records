package me.eyrim.foodrecords2.mainactivity;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;

import me.eyrim.foodrecords2.Recipe;

/**
 * This class manages the recipes post-load.<br/>
 * For loading purposes, refer to <code>me.eyrim.foodrecords2.mainactivity.RecipeLoader</code>
 */
public class RecipeManager {
    public static void saveRecipe(Recipe recipe, Context context) {
        try (FileOutputStream fos = context.openFileOutput(RecipeLoader.getRecipePaths().length + ".json", Context.MODE_PRIVATE)) {
            // Gson to convert obj to json
            Gson gson = new GsonBuilder().serializeNulls().create();
            // Get the json rep of the recipe passed in
            String json = gson.toJson(recipe);

            // Write it to a new file
            fos.write(json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
