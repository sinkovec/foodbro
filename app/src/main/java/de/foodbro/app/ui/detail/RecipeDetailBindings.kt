package de.foodbro.app.ui.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.ui.recipes.RecipesAdapter

@BindingAdapter("android:items")
fun setIngredientItems(listView: RecyclerView, items: List<Ingredient>?) {
    items?.let {
        (listView.adapter as IngredientsAdapter).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setPreparationItems(listView: RecyclerView, items: List<String>?) {
    items?.let {
        (listView.adapter as PreparationsAdapter).submitList(it)
    }
}