package de.foodbro.app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import de.foodbro.app.databinding.ListItemDetailIngredientBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter

class IngredientsAdapter : AbstractIngredientsAdapter<ListItemDetailIngredientBinding>() {

    override fun getViewDataBinding(layoutInflater: LayoutInflater, parent: ViewGroup): ListItemDetailIngredientBinding {
        return ListItemDetailIngredientBinding.inflate(layoutInflater, parent, false)
    }

    override fun getViewHolder(binding: ListItemDetailIngredientBinding):
            AbstractIngredientsAdapter.ViewHolder<ListItemDetailIngredientBinding> {
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ListItemDetailIngredientBinding) :
        AbstractIngredientsAdapter.ViewHolder<ListItemDetailIngredientBinding>(binding) {

        override fun bind(ingredient: Ingredient) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }
    }
}

