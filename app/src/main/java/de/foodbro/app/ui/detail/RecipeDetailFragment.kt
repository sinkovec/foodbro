package de.foodbro.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import de.foodbro.app.R
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

import javax.inject.Inject

class RecipeDetailFragment : DaggerFragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewModel.setup(args.recipeId)
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupName()
        setupDescription()
        setupIngredientsList()
        setupPreparationsList()
        setupFab()
    }

    private fun setupName() {
        viewModel.recipe.observe(viewLifecycleOwner) {
            recipe_name.text = it.name
        }
    }

    private fun setupDescription() {
        viewModel.recipe.observe(viewLifecycleOwner) {
            recipe_description.text = it.description
        }
    }

    private fun setupIngredientsList() {
        val adapter = IngredientsAdapter()
        ingredients_list.adapter = adapter
        ingredients_list.layoutManager = LinearLayoutManager(context)
        viewModel.ingredients.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupPreparationsList() {
        val adapter = PreparationsAdapter()
        preparations_list.adapter = adapter
        preparations_list.layoutManager = LinearLayoutManager(context)
        viewModel.recipe.observe(viewLifecycleOwner) {
            adapter.submitList(it.preparation)
        }
    }

    private fun setupFab() {
        fab_edit_recipe.setOnClickListener {
            Toast.makeText(context, "Todo: Open Edit View", Toast.LENGTH_SHORT).show()
        }
    }

}
