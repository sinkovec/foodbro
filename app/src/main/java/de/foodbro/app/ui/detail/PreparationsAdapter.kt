package de.foodbro.app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import de.foodbro.app.databinding.ListItemDetailPreparationBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter

class PreparationsAdapter : AbstractPreparationAdapter<ListItemDetailPreparationBinding>() {

    override fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ListItemDetailPreparationBinding {
        return ListItemDetailPreparationBinding.inflate(layoutInflater, parent, false)
    }

    override fun getViewHolder(binding: ListItemDetailPreparationBinding):
            AbstractPreparationAdapter.ViewHolder<ListItemDetailPreparationBinding> {
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ListItemDetailPreparationBinding) :
        AbstractPreparationAdapter.ViewHolder<ListItemDetailPreparationBinding>(binding) {

        override fun bind(item: PreparationStep) {
            binding.preparation = item
        }

    }
}