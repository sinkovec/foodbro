package de.foodbro.app.ui.recipes

import androidx.lifecycle.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import de.foodbro.app.ui.Event
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
    recipeRepository: RecipeRepository) : ViewModel() {

    val items: LiveData<List<Recipe>> = recipeRepository.getAll()

    private val _openRecipeEvent = MutableLiveData<Event<Int>>()
    val openRecipeEvent: LiveData<Event<Int>> = _openRecipeEvent

    fun openRecipe(recipeId: Int) {
        _openRecipeEvent.value = Event(recipeId)
    }
}