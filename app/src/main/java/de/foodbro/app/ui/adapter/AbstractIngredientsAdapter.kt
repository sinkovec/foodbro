package de.foodbro.app.ui.adapter

import androidx.databinding.ViewDataBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.util.callbacks.IngredientDiffCallback

abstract class AbstractIngredientsAdapter<T : ViewDataBinding> : AbstractAdapter<T, Ingredient>(
    IngredientDiffCallback()
) {
    abstract class ViewHolder<T : ViewDataBinding>(binding: T) : AbstractAdapter.ViewHolder<T,Ingredient>(binding)
}
