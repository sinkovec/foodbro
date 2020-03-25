package de.foodbro.app.ui.edit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemEditIngredientBinding
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Units
import de.foodbro.app.ui.adapter.AbstractAdapter
import de.foodbro.app.ui.adapter.AbstractIngredientsAdapter
import javax.inject.Inject

class IngredientsAdapter @Inject constructor(
    private val context: Context
) : AbstractIngredientsAdapter<ListItemEditIngredientBinding>() {

    override fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ListItemEditIngredientBinding =
        ListItemEditIngredientBinding.inflate(layoutInflater, parent, false)

    override fun getViewHolder(binding: ListItemEditIngredientBinding):
            AbstractAdapter.ViewHolder<ListItemEditIngredientBinding, Ingredient> =
        ViewHolder(binding)

    inner class ViewHolder(binding: ListItemEditIngredientBinding) :
        AbstractAdapter.ViewHolder<ListItemEditIngredientBinding, Ingredient>(binding) {

        override fun bind(item: Ingredient) {
            binding.ingredient = item
            setupUnitDropdown()
        }
        private fun setupUnitDropdown() {
            val adapter =
                UnitsAdapter(context, R.layout.list_item_edit_ingredient_units, Units.values())
            binding.unitDropdown.setAdapter(adapter)
        }
    }
}

class UnitsAdapter(context: Context, layout: Int, var values: Array<Units>) :
    ArrayAdapter<Units>(context, layout, values) {

    // use a 'non-filtering' filter
    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?) = FilterResults().apply {
            values = this@UnitsAdapter.values
            count = this@UnitsAdapter.values.size
        }
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) =
            notifyDataSetChanged()
    }

    override fun getFilter() = filter
}

