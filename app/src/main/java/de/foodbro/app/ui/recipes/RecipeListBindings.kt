package de.foodbro.app.ui.recipes

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Recipe

@BindingAdapter("android:items")
fun setItems(listView: RecyclerView, items: List<Recipe>?) {
    items?.let {
        (listView.adapter as RecipesAdapter).submitList(it)
    }
}