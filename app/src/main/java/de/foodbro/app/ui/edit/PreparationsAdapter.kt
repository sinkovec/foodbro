package de.foodbro.app.ui.edit

import de.foodbro.app.R
import de.foodbro.app.databinding.ListItemEditPreparationBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.ui.adapter.AbstractPreparationAdapter
import java.util.*

class PreparationsAdapter : AbstractPreparationAdapter<ListItemEditPreparationBinding>() {

    override fun getLayout() = R.layout.list_item_edit_preparation

    override fun getViewHolder(binding: ListItemEditPreparationBinding):
            AbstractPreparationAdapter.ViewHolder<ListItemEditPreparationBinding> =
        ViewHolder(binding)

    fun swap(fromPosition: Int, toPosition: Int) {
        val list = currentList.toMutableList()
        Collections.swap(list, fromPosition, toPosition)

        list[fromPosition].pos = toPosition + 1
        list[toPosition].pos = fromPosition + 1

        if (fromPosition < toPosition) {
            notifyItemMoved(fromPosition, toPosition)
        } else {
            notifyItemMoved(toPosition, fromPosition)
        }
        notifyItemChanged(fromPosition)
        notifyItemChanged(toPosition)
    }

    inner class ViewHolder(binding: ListItemEditPreparationBinding) :
        AbstractPreparationAdapter.ViewHolder<ListItemEditPreparationBinding>(binding) {

        override fun bind(item: PreparationStep) {
            binding.preparation = item
        }

    }
}