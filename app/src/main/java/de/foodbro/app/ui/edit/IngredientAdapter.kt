package de.foodbro.app.ui.edit

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemIngredientEditBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.ui.adapter.AbstractAdapter
import de.foodbro.app.ui.adapter.AbstractIngredientAdapter

class IngredientAdapter : AbstractIngredientAdapter<ListItemIngredientEditBinding>() {

    override fun getLayout() = R.layout.list_item_ingredient_edit

    override fun getViewHolder(binding: ListItemIngredientEditBinding):
            AbstractIngredientAdapter.ViewHolder<ListItemIngredientEditBinding> =
        ViewHolder(binding)

    inner class ViewHolder(binding: ListItemIngredientEditBinding) :
        AbstractIngredientAdapter.ViewHolder<ListItemIngredientEditBinding>(binding) {

        override fun bind(item: Ingredient) {
            binding.ingredient = item
        }
    }
}