package de.foodbro.app.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditIngredientBinding
import javax.inject.Inject

class RecipeEditIngredientFragment : DaggerFragment() {

    lateinit var viewModel: RecipeEditViewModel

    @Inject
    lateinit var listAdapter: IngredientsAdapter

    private lateinit var viewDataBinding: FragmentRecipeEditIngredientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditIngredientBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditIngredientFragment.viewModel
            lifecycleOwner = this@RecipeEditIngredientFragment.viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewDataBinding.ingredientsList.adapter = listAdapter
        viewModel.ingredients.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}
