package de.foodbro.app.ui.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.foodbro.app.model.Recipe
import de.foodbro.app.databinding.ListItemRecipeBinding

class RecipeAdapter: ListAdapter<Recipe, RecyclerView.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeViewHolder(
            ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recipe = getItem(position)
        (holder as RecipeViewHolder).bind(recipe)
    }

    class RecipeViewHolder(private val binding: ListItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.recipe?.let { recipe ->
                    navigateToRecipeDetail(recipe, it)
                }
            }
        }

        private fun navigateToRecipeDetail(recipe: Recipe, view: View) {
            val direction = RecipeListFragmentDirections.actionFragmentRecipeListDestToFragmentRecipeDetailDest(recipe.id)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Recipe) {
            binding.apply {
                recipe = item
            }.executePendingBindings()
        }
    }
}

private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}