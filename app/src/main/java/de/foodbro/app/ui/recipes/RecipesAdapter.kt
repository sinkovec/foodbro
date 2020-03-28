package de.foodbro.app.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.databinding.ListItemRecipesBinding
import de.foodbro.app.model.Recipe
import de.foodbro.app.util.callbacks.RecipeDiffCallback

class RecipesAdapter constructor(
    private val viewModel: RecipesViewModel
) : ListAdapter<Recipe, RecipesAdapter.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemRecipesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ListItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.viewModel = viewModel
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }
}