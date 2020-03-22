package de.foodbro.app.ui.detail

import androidx.lifecycle.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository) : ViewModel() {

    private val _recipeId = MutableLiveData<Int>()

    private val _recipe = _recipeId.switchMap {
        recipeRepository.getById(it)
    }
    val recipe: LiveData<Recipe> = _recipe

    val ingredients = _recipeId.switchMap {
        ingredientRepository.getAllByRecipeId(it)
    }

    fun setup(recipeId: Int) {
        _recipeId.value = recipeId
    }
}
