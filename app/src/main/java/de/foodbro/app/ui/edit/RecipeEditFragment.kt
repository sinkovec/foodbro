package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipeEditBinding
import javax.inject.Inject

@AndroidEntryPoint
class RecipeEditFragment : Fragment() {

    private val args: RecipeEditFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: RecipeEditViewModel.AssistedFactory

    private val viewModel: RecipeEditViewModel by viewModels {
        RecipeEditViewModel.provideFactory(
            viewModelFactory,
            args.recipeId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentRecipeEditBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            ingredientList.adapter = IngredientAdapter()
        }.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.recipe_edit_action_save -> {
                viewModel.saveRecipe()
                navigateToRecipeDetail()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToRecipeDetail() {
        val action = RecipeEditFragmentDirections.actionFragmentRecipeEditDestToFragmentRecipeDetailDest(args.recipeId)
        findNavController().navigate(action)
    }
}