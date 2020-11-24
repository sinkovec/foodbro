package de.foodbro.app.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.data.Recipe

@BindingAdapter("android:items")
fun setRecipeItems(listView: RecyclerView, items: List<Recipe>?) {
    items?.let {
        (listView.adapter as RecipeAdapter).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setStringItems(listView: RecyclerView, items: List<String>?) {
    if (listView.adapter is IngredientAdapter) {
        items?.let {
            (listView.adapter as IngredientAdapter).submitList(it)
        }
    }
    else if (listView.adapter is PreparationStepAdapter) {
        items?.let {
            (listView.adapter as PreparationStepAdapter).submitList(it)
        }
    }
}