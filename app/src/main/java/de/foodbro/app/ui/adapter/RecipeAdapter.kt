package de.foodbro.app.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.foodbro.app.data.Recipe
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
//        init {
//            binding.setClickListener {
//                binding.plant.let { plant ->
//                    navigateToPlant(plant, it)
//                }
//            }
//        }
//
//        private fun navigateToPlant(plant: Plant, view: View) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            view.findNavController().navigate(direction)
//        }

        fun bind(item: Recipe) {
            binding.apply {
                recipe = item
                executePendingBindings()
            }
        }
    }
}

private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}