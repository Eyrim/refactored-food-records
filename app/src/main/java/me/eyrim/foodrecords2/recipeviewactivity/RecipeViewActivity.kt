package me.eyrim.foodrecords2.recipeviewactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.Recipe
import me.eyrim.foodrecords2.mainactivity.RecipeLoader

class RecipeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        val recipeId: String? = intent.getStringExtra("id");
        //findViewById<TextView>(R.id.test).text = recipeId;

        val recipe: Recipe = RecipeLoader.getRecipeFromId(recipeId, this);
        populateActivity(recipe);
    }

    private fun populateActivity(recipe: Recipe) {
        findViewById<TextView>(R.id.record_name).text = recipe.recipeName;
        findViewById<TextView>(R.id.record_desc).text = recipe.recipeDesc;
    }
}