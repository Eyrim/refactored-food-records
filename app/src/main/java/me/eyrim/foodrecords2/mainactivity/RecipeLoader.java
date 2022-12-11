package me.eyrim.foodrecords2.mainactivity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        return loadRecipesFromJson(context);
    }

    // TODO: 13/11/2022 Refactor 
    public static Recipe getRecipeFromId(String recipeId, Context context) {
        Recipe recipe;
        String[] allPaths;
        Gson gson = new GsonBuilder().serializeNulls().create();

        try {
            allPaths = getRecipePaths(true);
            // If the requested recipe exists
            if (Arrays.asList(allPaths).contains(recipeId + ".json")) {
                // Convert the file to a recipe object
                recipe = gson.fromJson(FileHandling.readFileToString(baseFilePath + recipeId + ".json", context), Recipe.class);
            } else { // If the recipe didn't exist, then throw an exception. This will be immediately managed by the catch clause
                throw new FileNotFoundException("Requested recipe ID: " + recipeId + " not found");
            }
        } catch (Exception e) {
            // Print exception details
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return recipe;
    }

    private static void insertTestFiles(Context context) {
        String[] json = new String[] {
                "{\n" +
                        "\t\"recipe_name\": \"My Test Recipe 1\",\n" +
                        "\t\"recipe_id\": \"0\",\n" +
                        "\t\"recipe_desc\": \"This is a test description for My Test Recipe 1\",\n" +
                        "\t\"ingredients\": [\n" +
                        "\t\t\"Tomato\",\n" +
                        "\t\t\"Carrot\"\n" +
                        "\t]\n" +
                "}",
                "{\n" +
                        "\t\"recipe_name\": \"My Test Recipe 2\",\n" +
                        "\t\"recipe_id\": \"1\",\n" +
                        "\t\"recipe_desc\": \"aaaaaaaaaaaaaaaaa\",\n" +
                        "\t\"ingredients\": [\n" +
                        "\t\t\"Tomato\",\n" +
                        "\t\t\"Carrot\",\n" +
                        "\t\t\"Nightmares\"\n" +
                        "\t]\n" +
                "}"
        };

        try (FileOutputStream fos = context.openFileOutput("0.json", Context.MODE_PRIVATE)) {
            fos.write(json[0].getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = context.openFileOutput("1.json", Context.MODE_PRIVATE)) {
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
    private static Recipe[] loadRecipesFromJson(Context context) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Recipe[] recipes;

        try {
            // Get every recipe file path in array
            String[] recipePaths = getRecipePaths(false);
            recipes = new Recipe[recipePaths.length];

            for (int i = 0; i < recipes.length; i++) {
                // Serialise the recipe as a Recipe object and add it to the array
                recipes[i] = gson.fromJson(FileHandling.readFileToString(recipePaths[i], context), Recipe.class);
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
    public static String[] getRecipePaths(boolean splitted) throws IOException {
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

        // TODO: 11/12/2022 Desperately needs a refactor
        if (splitted) {
            String[] recipesSplit = new String[recipes.size()];
            String[] current = null;

            for (int i = 0; i < recipes.size(); i++) {
                current = recipes.get(i).split("/");
                recipesSplit[i] = current[current.length - 1];
            }

            return recipesSplit;
        }

        // Return the recipes as a static array
        return recipes.toArray(new String[recipes.size()]);
    }
}
