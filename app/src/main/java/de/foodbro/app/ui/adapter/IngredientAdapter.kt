package de.foodbro.app.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.foodbro.app.data.Recipe
import de.foodbro.app.databinding.ListItemIngredientDetailBinding
import de.foodbro.app.databinding.ListItemRecipeBinding
import de.foodbro.app.ui.RecipeListFragmentDirections

class IngredientAdapter: ListAdapter<String, RecyclerView.ViewHolder>(IngredientDiffCallback()) {

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
        fun bind(item: String) {
            binding.apply {
                ingredient = item
                executePendingBindings()
            }
        }
    }
}

private class IngredientDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}