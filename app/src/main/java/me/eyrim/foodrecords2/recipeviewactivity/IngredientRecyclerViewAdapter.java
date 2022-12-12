package me.eyrim.foodrecords2.recipeviewactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.eyrim.foodrecords2.Ingredient;
import me.eyrim.foodrecords2.R;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientViewHolder> {
    private final Ingredient[] ingredients;

    public IngredientRecyclerViewAdapter(Ingredient[] ingredients, Context context) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_row, parent, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        //imageView.setImageResource(this.ingredients[position]);

        holder.imageView.setImageResource(this.ingredients[position].getIngredientDrawableTag());
    }

    @Override
    public int getItemCount() {
        return this.ingredients.length;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ingredient_image);
        }
    }
}
