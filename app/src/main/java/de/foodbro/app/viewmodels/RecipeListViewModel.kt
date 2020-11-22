package de.foodbro.app.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.foodbro.app.data.Recipe
import de.foodbro.app.repository.RecipeRepository

class RecipeListViewModel @ViewModelInject internal constructor(
    recipeRepository: RecipeRepository
): ViewModel() {

    val recipes: LiveData<List<Recipe>> = recipeRepository.getRecipes()
}