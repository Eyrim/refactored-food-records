package me.eyrim.foodrecords2.mainactivity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import me.eyrim.foodrecords2.FileHandling;
import me.eyrim.foodrecords2.Recipe;

public class RecipeLoader {
    // TODO: 24/10/2022 Should this be just stored wherever?
    private static String baseFilePath;

    public static Recipe[] getRecipes(Context context, boolean insertTestFiles) {
        baseFilePath = new File(context.getFilesDir().getAbsolutePath()).getAbsolutePath();

        if (insertTestFiles) {
            insertTestFiles(context);
        }

        return loadRecipesFromJson();
    }

    public static Recipe getRecipeFromId(String recipeId) {
        Recipe recipe = null;


        return recipe;
    }

    private static void insertTestFiles(Context context) {
        String[] json = new String[] {
                "{\n" +
                        "\t\"recipe_name\": \"My Test Recipe 1\",\n" +
                        "\t\"recipe_desc\": \"This is a test description for My Test Recipe 1\",\n" +
                        "\t\"ingredients\": [\n" +
                        "\t\t\"Tomato\",\n" +
                        "\t\t\"Carrot\"\n" +
                        "\t]\n" +
                "}",
                "{\n" +
                        "\t\"recipe_name\": \"My Test Recipe 2\",\n" +
                        "\t\"recipe_desc\": \"aaaaaaaaaaaaaaaaa\",\n" +
                        "\t\"ingredients\": [\n" +
                        "\t\t\"Tomato\",\n" +
                        "\t\t\"Carrot\",\n" +
                        "\t\t\"Nightmares\"\n" +
                        "\t]\n" +
                "}"
        };

        try (FileOutputStream fos = context.openFileOutput("recipe1.json", Context.MODE_PRIVATE)) {
            fos.write(json[0].getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = context.openFileOutput("recipe2.json", Context.MODE_PRIVATE)) {
            fos.write(json[1].getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the recipes from the base file path<br\>
     * This method loads every subsequent json file under the recipes directory and attempts to<br\>
     * Read it as a recipe.
     * @return Recipe[] containing the serialised form of every recipe.json file<br\>Or null if an error was encountered
     */
    private static Recipe[] loadRecipesFromJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Recipe[] recipes;

        try {
            // Get every recipe file path in array
            String[] recipePaths = getRecipePaths();
            recipes = new Recipe[recipePaths.length];

            for (int i = 0; i < recipes.length; i++) {
                // Serialise the recipe as a Recipe object and add it to the array
                recipes[i] = gson.fromJson(FileHandling.readFileToString(recipePaths[i]), Recipe.class);
            }
        } catch (Exception e) {
            // Print the exception details
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return recipes;
    }

    /**
     * Gets the recipe paths from disk.<br\></br\>
     * This method will only return the paths of json files under the base directory
     * @return String[] containing the absolute paths of any json files under the base directory
     * @throws IOException If the base directory does not exist. Invocation of this exception will create the directory
     */
    private static String[] getRecipePaths() throws IOException {
        File baseDir = new File(baseFilePath);
        File[] children;
        List<String> recipes = new ArrayList<>(10);

        // If the base directory doesn't exist
        if (!baseDir.exists()) {
            baseDir.mkdir();
            throw new IOException("Base directory: " + baseDir.getAbsolutePath() + " did not exist. It has been created");
        }

        children = baseDir.listFiles();

        // If the directory has children at all
        if (children.length > 0) {
            // Iterate through every child
            for (File file : children) {
                if (file.isDirectory()) {
                    continue;
                }
                if (file.isHidden()) {
                    continue;
                }

                // If the file is a Json file
                if (file.getPath().matches(".*\\.json")) {
                    recipes.add(file.getAbsolutePath());
                }
            }
        }

        // Return the recipes as a static array
        return recipes.toArray(new String[recipes.size()]);
    }
}
