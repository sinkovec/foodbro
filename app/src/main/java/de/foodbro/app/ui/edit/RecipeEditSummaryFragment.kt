package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditSummaryBinding
import javax.inject.Inject

class RecipeEditSummaryFragment : DaggerFragment() {

    lateinit var viewModel: RecipeEditViewModel

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter

    private lateinit var viewDataBinding: FragmentRecipeEditSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditSummaryBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditSummaryFragment.viewModel
            lifecycleOwner = this@RecipeEditSummaryFragment.viewLifecycleOwner
        }
        return viewDataBinding.root
    }
}
