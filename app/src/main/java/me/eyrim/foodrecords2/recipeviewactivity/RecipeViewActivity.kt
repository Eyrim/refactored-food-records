package me.eyrim.foodrecords2.recipeviewactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.eyrim.foodrecords2.Ingredient
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.Recipe
import me.eyrim.foodrecords2.mainactivity.RecipeLoader

class RecipeViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView;
    private lateinit var layoutManager: RecyclerView.LayoutManager;
    private lateinit var recyclerViewAdapater: IngredientRecyclerViewAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        val recipeId: String? = intent.getStringExtra("id");

        this.recyclerView = findViewById<RecyclerView>(R.id.gridViewRecycler);
        // There will be 2 ingredients per line
        this.layoutManager = GridLayoutManager(this, 2);
        this.recyclerView.layoutManager = this.layoutManager;
        val ingredients: Array<Ingredient> = RecipeLoader.getRecipeFromId(recipeId, this).ingredients;
        this.recyclerViewAdapater = IngredientRecyclerViewAdapter(ingredients, this);
        this.recyclerView.adapter = this.recyclerViewAdapater;
        this.recyclerView.setHasFixedSize(true);


        //findViewById<TextView>(R.id.).text = recipeId;

        val recipe: Recipe = RecipeLoader.getRecipeFromId(recipeId, this);

        findViewById<TextView>(R.id.recipe_title).text = recipe.recipeName;
        findViewById<TextView>(R.id.recipe_desc).text = recipe.recipeDesc;
    }
}