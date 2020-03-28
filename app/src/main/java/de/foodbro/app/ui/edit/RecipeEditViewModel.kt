package de.foodbro.app.ui.edit

import androidx.lifecycle.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.Units
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

    private var isNewIngredient = true
    private val _selectedIngredient = MutableLiveData<Ingredient>()
    val selectedIngredient: LiveData<Ingredient> = _selectedIngredient

    private val _recipeUpdatedEvent = MutableLiveData<Event<Unit>>()
    val recipeUpdatedEvent: LiveData<Event<Unit>> = _recipeUpdatedEvent

    private val _openBottomSheetEvent = MutableLiveData<Event<Unit>>()
    val openBottomSheetEvent: LiveData<Event<Unit>> = _openBottomSheetEvent

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
        val ingredient = Ingredient()
        isNewIngredient = true
        openBottomSheet(ingredient)
    }

    fun editIngredient(ingredient: Ingredient) {
        isNewIngredient = false
        openBottomSheet(ingredient)
    }

    private fun openBottomSheet(ingredient: Ingredient) {
        _openBottomSheetEvent.value = Event(Unit)
        _selectedIngredient.value = ingredient
    }

    fun closeBottomSheet() {
        val ingredient = _selectedIngredient.value
        if (isNewIngredient && ingredient != null && isIngredientValid(ingredient)) {
            ingredients.value?.add(_selectedIngredient.value!!)
        }
        ingredients.notifyObserver()
    }

    private fun isIngredientValid(ingredient: Ingredient) = ingredient.name.isNotBlank()

    fun isChecked(unit: Units) = _selectedIngredient.value?.unit?.equals(unit) ?: false

    fun check(unit: Units) {
        val currentUnit = _selectedIngredient.value?.unit
        if (currentUnit != null && currentUnit == unit) {
            _selectedIngredient.value?.unit = null
        } else {
            _selectedIngredient.value?.unit = unit
        }
        _selectedIngredient.notifyObserver()
    }

    fun addPreparationStep() {
        preparationSteps.value?.add(PreparationStep())
        preparationSteps.notifyObserver()
    }
}
