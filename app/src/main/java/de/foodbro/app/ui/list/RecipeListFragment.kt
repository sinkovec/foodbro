package de.foodbro.app.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.foodbro.app.databinding.FragmentRecipeListBinding

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentRecipeListBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            recipeList.adapter = RecipeAdapter()
        }.root
    }

}