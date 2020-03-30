package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditIngredientBinding
import de.foodbro.app.di.ViewModelFactory
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.edit.dialogs.UnitsDialogFragment
import javax.inject.Inject

class RecipeEditIngredientFragment : DaggerFragment() {

    companion object {
        fun newInstance() = RecipeEditIngredientFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[RecipeEditViewModel::class.java]
    }

    private lateinit var viewDataBinding: FragmentRecipeEditIngredientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditIngredientBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditIngredientFragment.viewModel
            lifecycleOwner = this@RecipeEditIngredientFragment.viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listAdapter = IngredientsAdapter(viewModel)
        viewDataBinding.ingredientsList.adapter = listAdapter
        viewModel.ingredients.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

        viewModel.openBottomSheetEvent.observe(viewLifecycleOwner, EventObserver {
            UnitsDialogFragment.newInstance(viewModel).show(parentFragmentManager, "dialog")
        })
    }
}
