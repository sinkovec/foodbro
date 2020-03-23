package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.detail.RecipeDetailFragmentDirections
import javax.inject.Inject

class RecipeEditFragment : DaggerFragment() {

    private val args: RecipeEditFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeEditViewModel

    private lateinit var viewDataBinding: FragmentRecipeEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.setup(args.recipeId)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.recipeUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action = RecipeEditFragmentDirections.actionRecipeEditFragmentDestToRecipesFragmentDest()
            findNavController().navigate(action)
        })
    }

}
