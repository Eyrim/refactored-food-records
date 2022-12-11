package me.eyrim.foodrecords2.recipeviewactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.Recipe
import me.eyrim.foodrecords2.mainactivity.RecipeLoader

class RecipeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
    }
}