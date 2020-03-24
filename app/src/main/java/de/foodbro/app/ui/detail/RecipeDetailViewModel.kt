package de.foodbro.app.ui.detail

import androidx.lifecycle.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.repository.RecipesRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.IntArg
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository) : ViewModel() {

    private val _recipeId = MutableLiveData<Int>()

    val recipe: LiveData<Recipe> = _recipeId.switchMap {
        recipeDetailRepository.observeRecipeById(it)
    }

    val ingredients = _recipeId.switchMap {
        recipeDetailRepository.observeIngredientsByRecipeId(it)
    }

    val preparationSteps = _recipeId.switchMap {
        recipeDetailRepository.observePreparationStepsByRecipeId(it)
    }

    private val _editRecipeEvent = MutableLiveData<Event<Unit>>()
    val editRecipeEvent: LiveData<Event<Unit>> = _editRecipeEvent

    fun setup(recipeId: IntArg) {
        _recipeId.value = recipeId.arg
    }

    fun editRecipe() {
        _editRecipeEvent.value = Event(Unit)
    }
}
