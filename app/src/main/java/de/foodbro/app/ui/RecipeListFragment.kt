package de.foodbro.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.foodbro.app.databinding.FragmentRecipeListBinding
import de.foodbro.app.ui.adapter.RecipeAdapter
import de.foodbro.app.viewmodels.RecipeListViewModel

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = RecipeAdapter()
        binding.recipeList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: RecipeAdapter) {
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }
    }

}