package me.eyrim.foodrecords2.mainactivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.marginBottom
import me.eyrim.foodrecords2.Recipe
import androidx.recyclerview.widget.RecyclerView
import me.eyrim.foodrecords2.R
import me.eyrim.foodrecords2.recipeviewactivity.RecipeViewActivity

class RecyclerViewAdapterMain(context: Context, recipes: Array<Recipe>): RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolder>() {
    private val context: Context;
    private val recipes: Array<Recipe>;

    init {
        this.context = context;
        this.recipes = recipes;
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var recipeName: TextView;
        var recipeDesc: TextView;
        // A bodged text view as a background
        var background: TextView;
        // The id of the recipe
            // This text view is invisible, it only exists to act as a primary key to find the requested recipe from a file
        var recipeId: TextView;

        init {
            recipeName = itemView.findViewById(R.id.record_name_text_view);
            recipeDesc = itemView.findViewById(R.id.record_desc_text_view);
            background = itemView.findViewById(R.id.background_color);
            recipeId = itemView.findViewById(R.id.record_id_text_view);


            itemView.isClickable = true;
            itemView.setOnClickListener { it: View ->
                // Gets the recipe id
                val recipeId: String = it.findViewById<TextView>(R.id.record_id_text_view).text as String;

                // Change to recipe view activity and attach the recipe id so the correct recipe can be loaded
                val intent: Intent = Intent(context, RecipeViewActivity::class.java);
                // Add the id so it can be loaded
                intent.putExtra("id", recipeId);

                context.startActivity(intent);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context);
        val view: View = inflater.inflate(R.layout.record_row, parent, false);

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recipeName.text = recipes[position].recipeName;
        holder.recipeDesc.text = recipes[position].recipeDesc;
        holder.recipeId.text = recipes[position].recipeId;

        if (position % 2 == 0) {
            // Lighter grey
            holder.background.setBackgroundColor(Color.parseColor("#636363"));
        } else {
            // Slightly darker grey
            holder.background.setBackgroundColor(Color.parseColor("#525252"));
        }
    }

    override fun getItemCount(): Int {
        return recipes.size;
    }
}