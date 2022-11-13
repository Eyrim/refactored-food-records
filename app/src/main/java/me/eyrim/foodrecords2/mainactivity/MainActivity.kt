package me.eyrim.foodrecords2.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.Recipe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the recipes from disk
        val recipes: Array<Recipe> = RecipeLoader.getRecipes(this, true);
        populateRecyclerView(recipes);
    }

    private fun populateRecyclerView(recipes: Array<Recipe>): Unit {
        val recyclerView: RecyclerView = findViewById(R.id.record_recycler_view);

        val adapter: RecyclerViewAdapterMain = RecyclerViewAdapterMain(this, recipes);
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(this);
    }
}