package de.foodbro.app.ui.edit

import androidx.lifecycle.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.IntArg
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeEditViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository) : ViewModel() {

    private val _recipeId = MutableLiveData<Int>()

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val portions = MutableLiveData<Int>()
    val difficulty = MutableLiveData<Int>()
    val preparationTime = MutableLiveData<Int>()

    val ingredients = MutableLiveData<List<Ingredient>>()

    val preparationSteps = MutableLiveData<List<PreparationStep>>()

    private val _recipeUpdatedEvent = MutableLiveData<Event<Unit>>()
    val recipeUpdatedEvent: LiveData<Event<Unit>> = _recipeUpdatedEvent

    fun setup(recipeId: IntArg?) {
        if (recipeId == null) {
            // No need to populate, it's a new recipe
            return
        }
        _recipeId.value = recipeId.arg
        loadRecipe(recipeId.arg)
        loadIngredients(recipeId.arg)
        loadPreparationSteps(recipeId.arg)
    }

    private fun loadRecipe(recipeId: Int) = viewModelScope.launch {
        recipeDetailRepository.getRecipeById(recipeId).let {
            if (it == null) {
                TODO("error message")
            } else {
                onRecipeLoaded(it)
            }
        }
    }

    private fun loadIngredients(recipeId: Int) = viewModelScope.launch {
        recipeDetailRepository.getIngredientsByRecipeId(recipeId).let {
            ingredients.value = it
        }
    }

    private fun loadPreparationSteps(recipeId: Int) = viewModelScope.launch {
        recipeDetailRepository.getPreparationStepsByRecipeId(recipeId).let {
            preparationSteps.value = it
        }
    }

    private fun onRecipeLoaded(recipe: Recipe) {
        name.value = recipe.name
        description.value = recipe.description
        portions.value = recipe.portions
        difficulty.value = recipe.difficulty
        preparationTime.value = recipe.preparationTime
    }

    fun save() {
        saveRecipe()
        saveIngredients()
        savePreparationSteps()
    }

    private fun saveRecipe() {
        val currentId = _recipeId.value
        val currentName = name.value
        val currentDescription = description.value.orEmpty()
        val currentDifficulty = difficulty.value
        val currentPortions = portions.value
        val currentPreparationTime = preparationTime.value

        if (currentName.isNullOrBlank()) {
            TODO("error message")
        }

        if (currentId == null) {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, currentDifficulty, currentPreparationTime))
        } else {
            insertRecipe(Recipe(currentName, currentDescription, currentPortions, currentDifficulty, currentPreparationTime, currentId))
        }
    }

    private fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeDetailRepository.insert(recipe)
        _recipeUpdatedEvent.value = Event(Unit)
    }

    private fun saveIngredients() = viewModelScope.launch {
        recipeDetailRepository.insertIngredients(_recipeId.value!!, ingredients.value.orEmpty())
    }

    private fun savePreparationSteps() = viewModelScope.launch {
        recipeDetailRepository.insertPreparation(_recipeId.value!!, preparationSteps.value.orEmpty())
    }
}
