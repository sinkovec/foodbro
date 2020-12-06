package de.foodbro.app.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipeDetailBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentRecipeDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeDetailFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            ingredientList.adapter = IngredientAdapter()
            preparationList.adapter = PreparationStepAdapter()
        }.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.recipe_detail_action_edit -> navigateToRecipeEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToRecipeEdit() {
        val direction =
            RecipeDetailFragmentDirections.actionFragmentRecipeDetailDestToFragmentRecipeEditDest(
                viewModel.recipeId
            )
        findNavController().navigate(direction)
    }
}