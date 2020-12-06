package de.foodbro.app.ui.detail

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemIngredientDetailBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.ui.adapter.AbstractIngredientAdapter

class IngredientAdapter : AbstractIngredientAdapter<ListItemIngredientDetailBinding>() {

    override fun getLayout() = R.layout.list_item_ingredient_detail

    override fun getViewHolder(binding: ListItemIngredientDetailBinding):
            AbstractIngredientAdapter.ViewHolder<ListItemIngredientDetailBinding> =
        ViewHolder(binding)

    inner class ViewHolder(binding: ListItemIngredientDetailBinding) :
        AbstractIngredientAdapter.ViewHolder<ListItemIngredientDetailBinding>(binding) {

        override fun bind(item: Ingredient) {
            binding.ingredient = item
        }
    }
}