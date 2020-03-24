package de.foodbro.app.ui.edit

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import de.foodbro.app.R

import de.foodbro.app.databinding.FragmentRecipeEditBinding
import de.foodbro.app.ui.EventObserver
import javax.inject.Inject

class RecipeEditFragment : DaggerFragment() {

    private val args: RecipeEditFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeEditViewModel

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter

    private lateinit var viewDataBinding: FragmentRecipeEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditFragment.viewModel
            lifecycleOwner = this@RecipeEditFragment.viewLifecycleOwner
            ingredientsList.adapter = this@RecipeEditFragment.ingredientsAdapter
            preparationsList.adapter = PreparationsAdapter()
        }
        viewModel.setup(args.recipeId)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()
        setupIngredientsList()
    }

    private fun setupNavigation() {
        viewModel.recipeUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                RecipeEditFragmentDirections.actionRecipeEditFragmentDestToRecipesFragmentDest()
            findNavController().navigate(action)
        })
    }

    private fun setupIngredientsList() {
        // set spacing between items
        val space = resources.getDimensionPixelSize(R.dimen.list_item_spacing)
        viewDataBinding.ingredientsList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View,
                parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.bottom = space
            }
        })
    }

}
