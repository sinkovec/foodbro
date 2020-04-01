package de.foodbro.app.ui.detail

import androidx.lifecycle.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.RecipeDetail
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.LongArg
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository) : ViewModel() {

    private val _recipeId = MutableLiveData<Long>()

    val recipeDetail: LiveData<RecipeDetail> = _recipeId.switchMap {
        recipeDetailRepository.observeById(it)
    }

    private val _editRecipeEvent = MutableLiveData<Event<Unit>>()
    val editRecipeEvent: LiveData<Event<Unit>> = _editRecipeEvent

    fun setup(recipeId: LongArg) {
        _recipeId.value = recipeId.arg
    }

    fun editRecipe() {
        _editRecipeEvent.value = Event(Unit)
    }
}
