package de.foodbro.app.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import de.foodbro.app.model.Ingredient

abstract class AbstractIngredientAdapter<T : ViewDataBinding> : AbstractAdapter<T, Ingredient>(IngredientDiffCallback()) {
    abstract class ViewHolder<T : ViewDataBinding>(binding: T) : AbstractAdapter.ViewHolder<T,Ingredient>(binding)
}

private class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {

    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}
