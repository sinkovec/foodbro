package de.foodbro.app.ui.recipes

import androidx.lifecycle.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
    recipeRepository: RecipeRepository) : ViewModel() {

    val items: LiveData<List<Recipe>> = recipeRepository.getAll()
}