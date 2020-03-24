package de.foodbro.app.ui.recipes

import androidx.lifecycle.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.RecipesRepository
import de.foodbro.app.ui.Event
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
    recipeRepository: RecipesRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = recipeRepository.observeAll()

    private val _openRecipeEvent = MutableLiveData<Event<Int>>()
    val openRecipeEvent: LiveData<Event<Int>> = _openRecipeEvent

    private val _newRecipeEvent = MutableLiveData<Event<Unit>>()
    val newRecipeEvent: LiveData<Event<Unit>> = _newRecipeEvent

    fun openRecipe(recipeId: Int) {
        _openRecipeEvent.value = Event(recipeId)
    }

    fun newRecipe() {
        _newRecipeEvent.value = Event(Unit)
    }
}