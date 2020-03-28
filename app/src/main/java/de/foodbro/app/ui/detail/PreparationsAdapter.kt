package de.foodbro.app.ui.detail

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemDetailPreparationBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter

class PreparationsAdapter : AbstractPreparationAdapter<ListItemDetailPreparationBinding>() {

    override fun getLayout() = R.layout.list_item_detail_preparation

    override fun getViewHolder(binding: ListItemDetailPreparationBinding) = ViewHolder(binding)

    inner class ViewHolder(binding: ListItemDetailPreparationBinding) :
        AbstractPreparationAdapter.ViewHolder<ListItemDetailPreparationBinding>(binding) {

        override fun bind(item: PreparationStep) {
            binding.preparation = item
        }

    }
}