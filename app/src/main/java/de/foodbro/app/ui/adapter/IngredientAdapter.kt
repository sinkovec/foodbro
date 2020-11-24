package de.foodbro.app.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.foodbro.app.databinding.ListItemIngredientDetailBinding
import de.foodbro.app.model.Ingredient

class IngredientAdapter: ListAdapter<Ingredient, RecyclerView.ViewHolder>(IngredientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IngredientViewHolder(
            ListItemIngredientDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ingredient = getItem(position)
        (holder as IngredientViewHolder).bind(ingredient)
    }

    class IngredientViewHolder(private val binding: ListItemIngredientDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ingredient) {
            binding.apply {
                ingredient = item
                executePendingBindings()
            }
        }
    }
}

private class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {

    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}