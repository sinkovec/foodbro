package de.foodbro.app.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment

import de.foodbro.app.R
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject

class RecipesFragment : DaggerFragment(), RecipesAdapter.ItemClickListener {

    @Inject
    lateinit var viewModel: RecipesViewModel

    private lateinit var listAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupListAdapter()
        setupFab()
    }

    private fun setupListAdapter() {
        listAdapter = RecipesAdapter(this)
        recipe_list.adapter = listAdapter
        recipe_list.layoutManager = LinearLayoutManager(context)
        viewModel.items.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun setupFab() {
        fab_add_recipe.setOnClickListener {
            Toast.makeText(context, "Todo: Open Edit View", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int) {
        val recipeId = listAdapter.recipes[position].id
        openRecipeDetails(recipeId)
    }

    private fun openRecipeDetails(recipeId: Int) {
        val action = RecipesFragmentDirections.actionRecipesFragmentDestToRecipeDetailFragment(recipeId)
        findNavController().navigate(action)
    }
}
