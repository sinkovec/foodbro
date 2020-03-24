package de.foodbro.app.ui.adapter

import androidx.databinding.ViewDataBinding
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.util.PreparationDiffCallback

abstract class AbstractPreparationAdapter<T : ViewDataBinding> : AbstractAdapter<T, PreparationStep>(PreparationDiffCallback()) {
    abstract class ViewHolder<T : ViewDataBinding>(binding: T) : AbstractAdapter.ViewHolder<T, PreparationStep>(binding)
}