package de.foodbro.app.ui.edit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemEditIngredientBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Units
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter
import javax.inject.Inject

class IngredientsAdapter @Inject constructor(
    private val context: Context
) : AbstractIngredientsAdapter<ListItemEditIngredientBinding>() {

    override fun getViewDataBinding(layoutInflater: LayoutInflater, parent: ViewGroup): ListItemEditIngredientBinding {
        return ListItemEditIngredientBinding.inflate(layoutInflater, parent, false)
    }

    override fun getViewHolder(binding: ListItemEditIngredientBinding):
            AbstractIngredientsAdapter.ViewHolder<ListItemEditIngredientBinding> {
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ListItemEditIngredientBinding) :
        AbstractIngredientsAdapter.ViewHolder<ListItemEditIngredientBinding>(binding) {

        override fun bind(ingredient: Ingredient) {
            binding.ingredient = ingredient
            setupUnitDropdown()
            binding.executePendingBindings()
        }

        private fun setupUnitDropdown() {
            val adapter = ArrayAdapter(context, R.layout.list_item_edit_ingredient_units, Units.values())
            binding.unitDropdown.setAdapter(adapter)
        }
    }
}

