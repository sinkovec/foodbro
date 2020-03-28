package de.foodbro.app.ui.edit

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemEditIngredientBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.ui.adapter.AbstractAdapter
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter
import javax.inject.Inject

class IngredientsAdapter (
    private val viewModel: RecipeEditViewModel
): AbstractIngredientsAdapter<ListItemEditIngredientBinding>() {

    override fun getLayout() = R.layout.list_item_edit_ingredient

    override fun getViewHolder(binding: ListItemEditIngredientBinding):
            AbstractAdapter.ViewHolder<ListItemEditIngredientBinding, Ingredient> =
        ViewHolder(binding)

    inner class ViewHolder(binding: ListItemEditIngredientBinding) :
        AbstractAdapter.ViewHolder<ListItemEditIngredientBinding, Ingredient>(binding) {

        override fun bind(item: Ingredient) {
            binding.viewModel = viewModel
            binding.ingredient = item
        }
    }
}

