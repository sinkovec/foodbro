package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import de.foodbro.app.databinding.FragmentRecipeEditIngredientBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.edit.unitsdialog.UnitsDialogFragment

class RecipeEditIngredientFragment : Fragment() {

    lateinit var viewModel: RecipeEditViewModel

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

        val unitsDialog = UnitsDialogFragment.newInstance(viewModel)
        viewModel.openBottomSheetEvent.observe(viewLifecycleOwner, EventObserver {
            unitsDialog.show(parentFragmentManager, "dialog")
        })
    }
}
