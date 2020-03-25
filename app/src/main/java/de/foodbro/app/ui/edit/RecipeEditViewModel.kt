package de.foodbro.app.ui.edit

import androidx.lifecycle.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.LongArg
import de.foodbro.app.util.notifyObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeEditViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository
) : ViewModel() {

    val recipe = MutableLiveData<Recipe>()
    val ingredients = MutableLiveData<MutableList<Ingredient>>()
    val preparationSteps = MutableLiveData<MutableList<PreparationStep>>()

    private val _recipeUpdatedEvent = MutableLiveData<Event<Unit>>()
    val recipeUpdatedEvent: LiveData<Event<Unit>> = _recipeUpdatedEvent

    fun setup(recipeId: LongArg?) {
        if (recipeId == null) {
            recipe.value = Recipe()
            ingredients.value = mutableListOf()
            preparationSteps.value = mutableListOf()
        } else {
            val id = recipeId.arg
            loadRecipe(id)
            loadIngredients(id)
            loadPreparationSteps(id)
        }
    }

    private fun loadRecipe(recipeId: Long) = viewModelScope.launch {
        recipeDetailRepository.getRecipeById(recipeId).let {
            if (it == null) {
                TODO("error message")
            } else {
                recipe.value = it
            }
        }
    }

    private fun loadIngredients(recipeId: Long) = viewModelScope.launch {
        recipeDetailRepository.getIngredientsByRecipeId(recipeId).let {
            ingredients.value = it.toMutableList()
        }
    }

    private fun loadPreparationSteps(recipeId: Long) = viewModelScope.launch {
        recipeDetailRepository.getPreparationStepsByRecipeId(recipeId).let {
            preparationSteps.value = it.toMutableList()
        }
    }

    fun saveRecipe() {
        val recipe = getRecipe()
        viewModelScope.launch {
            recipeDetailRepository.insert(
                recipe,
                ingredients.value.orEmpty(),
                preparationSteps.value.orEmpty()
            )
        }
        _recipeUpdatedEvent.value = Event(Unit)
    }

    private fun getRecipe(): Recipe {
        if (recipe.value == null) {
            TODO("error")
        }
        if (recipe.value?.name.isNullOrBlank()) {
            TODO("error message")
        }
        return recipe.value!!
    }

    fun addIngredient() {
        ingredients.value?.add(Ingredient())
        ingredients.notifyObserver()
    }

    fun addPreparationStep() {
        preparationSteps.value?.add(PreparationStep())
        preparationSteps.notifyObserver()
    }
}
