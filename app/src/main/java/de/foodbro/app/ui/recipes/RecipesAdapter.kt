package de.foodbro.app.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemRecipesBinding
import de.foodbro.app.model.Recipe

class RecipesAdapter(private val viewModel: RecipesViewModel) :
    ListAdapter<Recipe, RecipesAdapter.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemRecipesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    inner class ViewHolder(private val binding: ListItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: RecipesViewModel, recipe: Recipe) {
            binding.viewmodel = viewModel
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}