package de.foodbro.app.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe

@BindingAdapter("android:items")
fun setRecipeItems(listView: RecyclerView, items: List<Recipe>?) {
    items?.let {
        (listView.adapter as RecipeAdapter).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setIngredientItems(listView: RecyclerView, items: List<Ingredient>?) {
    items?.let {
        (listView.adapter as IngredientAdapter).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setPreparationStepItems(listView: RecyclerView, items: List<String>?) {
    items?.let {
        (listView.adapter as PreparationStepAdapter).submitList(it)
    }
}