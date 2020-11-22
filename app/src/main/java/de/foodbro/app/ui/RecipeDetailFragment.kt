package de.foodbro.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.HiltViewModelFactory
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipeDetailBinding
import de.foodbro.app.databinding.FragmentRecipeListBinding
import de.foodbro.app.ui.adapter.RecipeAdapter
import de.foodbro.app.viewmodels.RecipeDetailViewModel
import de.foodbro.app.viewmodels.RecipeListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: RecipeDetailViewModel.AssistedFactory

    private val viewModel: RecipeDetailViewModel by viewModels {
        RecipeDetailViewModel.provideFactory(
            viewModelFactory,
            args.recipeId
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentRecipeDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeDetailFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }
}