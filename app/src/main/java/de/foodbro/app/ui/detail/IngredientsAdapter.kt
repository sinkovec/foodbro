package de.foodbro.app.ui.detail

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemDetailIngredientBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter

class IngredientsAdapter : AbstractIngredientsAdapter<ListItemDetailIngredientBinding>() {

    override fun getLayout() = R.layout.list_item_detail_ingredient

    override fun getViewHolder(binding: ListItemDetailIngredientBinding) = ViewHolder(binding)

    inner class ViewHolder(binding: ListItemDetailIngredientBinding) :
        AbstractIngredientsAdapter.ViewHolder<ListItemDetailIngredientBinding>(binding) {

        override fun bind(item: Ingredient) {
            binding.ingredient = item
        }
    }
}

