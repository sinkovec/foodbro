package de.foodbro.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractAdapter<T : ViewDataBinding, S>(diffCallback: DiffUtil.ItemCallback<S>) :
    ListAdapter<S, AbstractAdapter.ViewHolder<T, S>>(diffCallback) {

    abstract fun getViewDataBinding(layoutInflater: LayoutInflater, parent: ViewGroup): T

    abstract fun getViewHolder(binding: T): ViewHolder<T, S>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T, S> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = getViewDataBinding(layoutInflater, parent)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<T, S>, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
    }

    override fun submitList(list: List<S>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    abstract class ViewHolder<T : ViewDataBinding, S>(protected val binding: T) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: S)

        internal fun bindItem(item: S) {
            bind(item)
            binding.executePendingBindings()
        }
    }
}