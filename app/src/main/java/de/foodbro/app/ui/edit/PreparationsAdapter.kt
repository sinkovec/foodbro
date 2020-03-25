package de.foodbro.app.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import de.foodbro.app.databinding.ListItemEditPreparationBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter

class PreparationsAdapter : AbstractPreparationAdapter<ListItemEditPreparationBinding>() {

    override fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ListItemEditPreparationBinding =
        ListItemEditPreparationBinding.inflate(layoutInflater, parent, false)

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