package de.foodbro.app.ui.edit

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemEditPreparationBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter

class PreparationsAdapter : AbstractPreparationAdapter<ListItemEditPreparationBinding>() {

    override fun getLayout() = R.layout.list_item_edit_preparation

    override fun getViewHolder(binding: ListItemEditPreparationBinding):
            AbstractPreparationAdapter.ViewHolder<ListItemEditPreparationBinding> =
        ViewHolder(binding)

    inner class ViewHolder(binding: ListItemEditPreparationBinding) :
        AbstractPreparationAdapter.ViewHolder<ListItemEditPreparationBinding>(binding) {

        override fun bind(item: PreparationStep) {
            binding.preparation = item
        }

    }
}