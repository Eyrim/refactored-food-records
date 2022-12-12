package me.eyrim.foodrecords2.recipeviewactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.Recipe
import me.eyrim.foodrecords2.mainactivity.RecipeLoader

class RecipeViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView;
    private lateinit var layoutManager: RecyclerView.LayoutManager;
    private lateinit var recyclerViewAdapater: IngredientRecyclerViewAdapter;
    private val ingredients: IntArray = intArrayOf(R.drawable.nessie1, R.drawable.nessie2, R.drawable.nessie3, R.drawable.nessie4, R.drawable.nessie5);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        this.recyclerView = findViewById<RecyclerView>(R.id.gridViewRecycler);
        // There will be 2 ingredients per line
        this.layoutManager = GridLayoutManager(this, 2);
        this.recyclerView.layoutManager = this.layoutManager;
        this.recyclerViewAdapater = IngredientRecyclerViewAdapter(this.ingredients);
        this.recyclerView.adapter = this.recyclerViewAdapater;
        this.recyclerView.setHasFixedSize(true);

        val recipeId: String? = intent.getStringExtra("id");
        //findViewById<TextView>(R.id.).text = recipeId;

        val recipe: Recipe = RecipeLoader.getRecipeFromId(recipeId, this);

        findViewById<TextView>(R.id.recipe_title).text = recipe.recipeName;
        findViewById<TextView>(R.id.recipe_desc).text = recipe.recipeDesc;
    }
}