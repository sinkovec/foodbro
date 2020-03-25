package de.foodbro.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import de.foodbro.app.databinding.FragmentRecipeDetailBinding
import de.foodbro.app.ui.EventObserver

import javax.inject.Inject

class RecipeDetailFragment : DaggerFragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeDetailViewModel

    private lateinit var viewDataBinding: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeDetailFragment.viewModel
            lifecycleOwner = this@RecipeDetailFragment.viewLifecycleOwner
            ingredientsList.adapter = IngredientsAdapter()
            preparationsList.adapter = PreparationsAdapter()
        }
        viewModel.setup(args.recipeId)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.editRecipeEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                RecipeDetailFragmentDirections.actionRecipeDetailFragmentDestToRecipeEditFragmentDest(
                    args.recipeId
                )
            findNavController().navigate(action)
        })
    }
}
