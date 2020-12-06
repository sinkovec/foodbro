package de.foodbro.app.ui.detail

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.foodbro.app.databinding.ListItemIngredientDetailBinding
import de.foodbro.app.databinding.ListItemPreparationDetailBinding

class PreparationStepAdapter: ListAdapter<String, RecyclerView.ViewHolder>(PreparationStepDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PreparationStepViewHolder(
            ListItemPreparationDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ingredient = getItem(position)
        (holder as PreparationStepViewHolder).bind(ingredient)
    }

    class PreparationStepViewHolder(private val binding: ListItemPreparationDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                preparationStep = item
                executePendingBindings()
            }
        }
    }
}

private class PreparationStepDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}