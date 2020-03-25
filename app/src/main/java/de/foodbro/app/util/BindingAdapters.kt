package de.foodbro.app.util

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.Units
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter
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
fun setPreparationStepItems(listView: RecyclerView, items: List<PreparationStep>?) {
    items?.let {
        (listView.adapter as AbstractPreparationAdapter<*>).submitList(it)
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

@BindingAdapter("android:text")
fun setUnit(view: EditText, value: Units?) {
    value?.let {
        if (view.text.toString() != it.toString()) {
            view.setText(it.toString())
        }
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getUnit(view: EditText): Units? {
    return Units.getValue(view.text.toString())
}