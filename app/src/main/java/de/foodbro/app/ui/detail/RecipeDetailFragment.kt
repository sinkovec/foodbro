package de.foodbro.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipeDetailBinding
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

import javax.inject.Inject

class RecipeDetailFragment : DaggerFragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeDetailViewModel

    private lateinit var viewDataBinding: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRecipeDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.setup(args.recipeId)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupIngredientsList()
        setupPreparationsList()
        setupFab()
    }

    private fun setupIngredientsList() {
        val listAdapter = IngredientsAdapter()
        viewDataBinding.ingredientsList.adapter = listAdapter

    }

    private fun setupPreparationsList() {
        val listAdapter = PreparationsAdapter()
        viewDataBinding.preparationsList.adapter = listAdapter
    }

    private fun setupFab() {
        fab_edit_recipe.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionRecipeDetailFragmentDestToRecipeEditFragment()
            findNavController().navigate(action)
        }
    }

}
