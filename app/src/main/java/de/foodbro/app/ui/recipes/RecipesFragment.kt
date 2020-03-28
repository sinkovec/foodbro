package de.foodbro.app.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import de.foodbro.app.databinding.FragmentRecipesBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.util.LongArg
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
            viewModel = this@RecipesFragment.viewModel
            lifecycleOwner = this@RecipesFragment.viewLifecycleOwner
            recipeList.adapter = RecipesAdapter(this@RecipesFragment.viewModel)
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openRecipeEvent.observe(viewLifecycleOwner, EventObserver {
            openRecipeDetails(it)
        })
        viewModel.newRecipeEvent.observe(viewLifecycleOwner, EventObserver {
            openNewRecipe()
        })
    }

    private fun openRecipeDetails(recipeId: Long) {
        val action =
            RecipesFragmentDirections.actionRecipesFragmentDestToRecipeDetailFragment(LongArg(recipeId))
        findNavController().navigate(action)
    }

    private fun openNewRecipe() {
        val action = RecipesFragmentDirections.actionRecipesFragmentDestToRecipeEditFragmentDest()
        findNavController().navigate(action)
    }
}
