package de.foodbro.app.util.callbacks

import androidx.recyclerview.widget.DiffUtil
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}

class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}

class PreparationDiffCallback : DiffUtil.ItemCallback<PreparationStep>() {
    override fun areItemsTheSame(oldItem: PreparationStep, newItem: PreparationStep): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PreparationStep, newItem: PreparationStep): Boolean {
        return oldItem == newItem
    }
}