package de.foodbro.app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository) : ViewModel() {

    lateinit var recipe: LiveData<Recipe>

    lateinit var ingredients: LiveData<List<Ingredient>>

    fun setup(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.getById(recipeId).let {
                recipe = it
            }
            ingredientRepository.getAllByRecipeId(recipeId).let {
                ingredients = it
            }
        }
    }
}
