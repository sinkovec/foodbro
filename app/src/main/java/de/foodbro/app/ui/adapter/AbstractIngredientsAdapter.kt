package de.foodbro.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.model.Ingredient
import de.foodbro.app.util.IngredientDiffCallback

abstract class AbstractIngredientsAdapter<T : ViewDataBinding> :
    ListAdapter<Ingredient, AbstractIngredientsAdapter.ViewHolder<T>>(IngredientDiffCallback()) {

    abstract fun getViewDataBinding(layoutInflater: LayoutInflater, parent: ViewGroup) : T

    abstract fun getViewHolder(binding: T): ViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = getViewDataBinding(layoutInflater, parent)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    abstract class ViewHolder<T : ViewDataBinding> (protected val binding: T) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(ingredient: Ingredient)
    }
}
