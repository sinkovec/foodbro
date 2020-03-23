package de.foodbro.app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.databinding.ListItemDetailPreparationBinding
import de.foodbro.app.util.StringDiffCallback

class PreparationsAdapter :
    ListAdapter<String, PreparationsAdapter.ViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemDetailPreparationBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ListItemDetailPreparationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(preparation: String) {
            binding.preparation = preparation
            binding.executePendingBindings()
        }
    }
}