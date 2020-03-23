package de.foodbro.app.ui.edit

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.IntArg
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeEditViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository) : ViewModel() {

    private val _recipeId = MutableLiveData<Int>()

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val portions = MutableLiveData<Int>(2)
    val preparationTime = MutableLiveData<Int>()

    private val _recipeUpdatedEvent = MutableLiveData<Event<Unit>>()
    val recipeUpdatedEvent: LiveData<Event<Unit>> = _recipeUpdatedEvent

    fun setup(recipeId: IntArg?) {
        if (recipeId == null) {
            // No need to populate, it's a new recipe
            return
        }
        _recipeId.value = recipeId.arg
        viewModelScope.launch {
            recipeRepository.getById(recipeId.arg).let {
                if (it == null) {
                    // todo: error message
                } else {
                    onRecipeLoaded(it)
                }
            }
        }
    }

    private fun onRecipeLoaded(recipe: Recipe) {
        name.value = recipe.name
        description.value = recipe.description
        portions.value = recipe.portions
    }

    fun saveRecipe() {
        val currentId = _recipeId.value
        val currentName = name.value
        val currentDescription = description.value.orEmpty()
        val currentPortions = portions.value

        if (currentName.isNullOrBlank()) {
            // todo: error message
            return
        }

        if (currentId == null) {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, emptyList()))
        } else {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, emptyList(), currentId))
        }
    }

    private fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.insert(recipe)
        _recipeUpdatedEvent.value = Event(Unit)
    }
}
