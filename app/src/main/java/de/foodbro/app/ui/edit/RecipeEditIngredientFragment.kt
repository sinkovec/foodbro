package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import de.foodbro.app.R

import de.foodbro.app.databinding.FragmentRecipeEditIngredientBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.edit.dialogs.IngredientDialogFragment
import javax.inject.Inject

class RecipeEditIngredientFragment : DaggerFragment() {

    companion object {
        fun newInstance() = RecipeEditIngredientFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireParentFragment(), viewModelFactory)[RecipeEditViewModel::class.java]
    }

    private lateinit var viewDataBinding: FragmentRecipeEditIngredientBinding

    private lateinit var ingredientDialogFragment: IngredientDialogFragment

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
        viewModel.recipeDetail.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it.ingredients)
        })

        viewModel.showPopupEvent.observe(viewLifecycleOwner, EventObserver {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_edit_ingredient -> {
                            viewModel.editSelectedIngredient()
                            true
                        }
                        R.id.action_delete_ingredient -> {
                            viewModel.deleteSelectedIngredient()
                            true
                        }
                        else -> false
                    }

                }
                inflate(R.menu.recipe_edit_ingredient_actions)
                show()
            }
        })

        viewModel.openBottomSheetEvent.observe(viewLifecycleOwner, EventObserver {
            ingredientDialogFragment = IngredientDialogFragment.newInstance(viewModel)
            ingredientDialogFragment.show(parentFragmentManager, "dialog")
        })

        viewModel.closeBottomSheetEvent.observe(viewLifecycleOwner, EventObserver {
            ingredientDialogFragment.dismiss()
        })
    }
}
