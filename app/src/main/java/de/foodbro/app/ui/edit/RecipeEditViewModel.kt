package de.foodbro.app.ui.edit

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
    val portions = MutableLiveData<Int>()
    val difficulty = MutableLiveData<Int>()
    val preparationTime = MutableLiveData<Int>()
    val preparationSteps = MutableLiveData<List<String>>()

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
        difficulty.value = recipe.difficulty
        preparationTime.value = recipe.preparationTime
        preparationSteps.value = recipe.preparationSteps
    }

    fun saveRecipe() {
        val currentId = _recipeId.value
        val currentName = name.value
        val currentDescription = description.value.orEmpty()
        val currentDifficulty = difficulty.value
        val currentPortions = portions.value
        val currentPreparationTime = preparationTime.value
        val currentPreparationSteps = preparationSteps.value.orEmpty()

        if (currentName.isNullOrBlank()) {
            // todo: error message
            return
        }

        if (currentId == null) {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, currentDifficulty, currentPreparationTime, currentPreparationSteps))
        } else {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, currentDifficulty, currentPreparationTime, currentPreparationSteps, currentId))
        }
    }

    private fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.insert(recipe)
        _recipeUpdatedEvent.value = Event(Unit)
    }
}
