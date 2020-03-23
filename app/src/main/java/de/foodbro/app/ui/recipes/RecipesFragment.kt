package de.foodbro.app.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment

import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipesBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.util.IntArg
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject

class RecipesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: RecipesViewModel

    private lateinit var viewDataBinding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipesBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()
        setupListAdapter()
    }

    private fun setupNavigation() {
        viewModel.openRecipeEvent.observe(viewLifecycleOwner, EventObserver {
            openRecipeDetails(it)
        })
        viewModel.newRecipeEvent.observe(viewLifecycleOwner, EventObserver {
            openNewRecipe()
        })
    }

    private fun openRecipeDetails(recipeId: Int) {
        val action =
            RecipesFragmentDirections.actionRecipesFragmentDestToRecipeDetailFragment(IntArg(recipeId))
        findNavController().navigate(action)
    }

    private fun openNewRecipe() {
        val action = RecipesFragmentDirections.actionRecipesFragmentDestToRecipeEditFragment()
        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val listAdapter = RecipesAdapter(viewModel)
        viewDataBinding.recipeList.adapter = listAdapter
    }

}
