package me.eyrim.foodrecords2.mainactivity

import android.content.Context
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

        init {
            recipeName = itemView.findViewById(R.id.record_name_text_view);
            recipeDesc = itemView.findViewById(R.id.record_desc_text_view);
            background = itemView.findViewById(R.id.background_color);

            // Hide the button, but still allow the user to click it
            itemView.findViewById<Button?>(R.id.click_recipe_button).setBackgroundColor(Color.TRANSPARENT);
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