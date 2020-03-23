package de.foodbro.app.util

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter
import de.foodbro.app.ui.detail.PreparationsAdapter
import de.foodbro.app.ui.recipes.RecipesAdapter

@BindingAdapter("android:items")
fun setItems(listView: RecyclerView, items: List<Recipe>?) {
    items?.let {
        (listView.adapter as RecipesAdapter).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setIngredientItems(listView: RecyclerView, items: List<Ingredient>?) {
    items?.let {
        (listView.adapter as AbstractIngredientsAdapter<*>).submitList(it)
    }
}

@BindingAdapter("android:items")
fun setPreparationItems(listView: RecyclerView, items: List<String>?) {
    items?.let {
        (listView.adapter as PreparationsAdapter).submitList(it)
    }
}

@BindingAdapter("android:text")
fun setInt(view: EditText, value: Int?) {
    value?.let {
        if (view.text.toString() != it.toString()) {
            view.setText(it.toString())
        }
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getInt(view: EditText): Int? {
    return try {
        view.text.toString().toInt()
    } catch (e: NumberFormatException) {
        null
    }
}