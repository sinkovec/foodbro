package de.foodbro.app.ui.edit

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import de.foodbro.app.model.*
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.ui.Event
import de.foodbro.app.util.LongArg
import de.foodbro.app.util.notifyObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeEditViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository
) : ViewModel() {

    val recipeDetail = MutableLiveData<RecipeDetail>()

    private val _recipeUpdatedEvent = MutableLiveData<Event<Long>>()
    val recipeUpdatedEvent: LiveData<Event<Long>> = _recipeUpdatedEvent

    private val _openImageEvent = MutableLiveData<Event<Unit>>()
    val openImageEvent: LiveData<Event<Unit>> = _openImageEvent

    private val _openTimePickerDialogEvent = MutableLiveData<Event<Unit>>()
    val openTimePickerDialogEvent: LiveData<Event<Unit>> = _openTimePickerDialogEvent

    private var isNewIngredient = true
    private val _selectedIngredient = MutableLiveData<Ingredient>()
    val selectedIngredient: LiveData<Ingredient> = _selectedIngredient

    private val _showPopupEvent = MutableLiveData<Event<View>>()
    val showPopupEvent: LiveData<Event<View>> = _showPopupEvent

    private val _openBottomSheetEvent = MutableLiveData<Event<Unit>>()
    val openBottomSheetEvent: LiveData<Event<Unit>> = _openBottomSheetEvent

    private val _closeBottomSheetEvent = MutableLiveData<Event<Unit>>()
    val closeBottomSheetEvent: LiveData<Event<Unit>> = _closeBottomSheetEvent

    private val _addedPreparationStepEvent = MutableLiveData<Event<Unit>>()
    val addedPreparationStepEvent: LiveData<Event<Unit>> = _addedPreparationStepEvent

    fun start(recipeId: LongArg?) {
        if (recipeId == null) {
            recipeDetail.value = RecipeDetail(Recipe(), mutableListOf(), mutableListOf())
        } else {
            val id = recipeId.arg
            loadRecipeDetail(id)
        }
    }

    private fun loadRecipeDetail(recipeId: Long) = viewModelScope.launch {
        recipeDetailRepository.getById(recipeId).let {
            if (it == null) {
                TODO("error message")
            } else {
                recipeDetail.value = it
            }
        }
    }

    fun saveRecipe() {
        if (recipeDetail.value == null) {
            TODO("error")
        }
        if (recipeDetail.value?.recipe?.name.isNullOrBlank()) {
            Log.e("=", "Recipe name is null or blank")
            return
        }
        val recipeDetail = recipeDetail.value!!
        viewModelScope.launch {
            val id = recipeDetailRepository.insert(recipeDetail)
            _recipeUpdatedEvent.value = Event(id)
        }
    }

    fun openImageEvent() {
        _openImageEvent.value = Event(Unit)
    }

    fun setImageUri(imageUri: Uri) {
        recipeDetail.value?.recipe?.imageUri = imageUri
        recipeDetail.notifyObserver()
    }

    fun openTimePickerDialog() {
        _openTimePickerDialogEvent.value = Event(Unit)
    }

    fun updatePreparationTime(hours: Int, minutes: Int) {
        recipeDetail.value?.recipe?.preparationTime = Pair(hours, minutes)
        recipeDetail.notifyObserver()
    }

    fun showPopupIngredientEvent(view: View, ingredient: Ingredient) {
        _selectedIngredient.value = ingredient
        _showPopupEvent.value = Event(view)
    }

    fun addIngredient() {
        isNewIngredient = true
        _selectedIngredient.value = Ingredient()
        openBottomSheetEvent()
    }

    fun editSelectedIngredient() {
        isNewIngredient = false
        openBottomSheetEvent()
    }

    fun deleteSelectedIngredient() {
        _selectedIngredient.value?.let {
            recipeDetail.value?.ingredients?.remove(it)
            recipeDetail.notifyObserver()
        }
    }

    private fun openBottomSheetEvent() {
        _openBottomSheetEvent.value = Event(Unit)
    }

    fun saveIngredient() {
        val ingredient = _selectedIngredient.value
        if (isNewIngredient && ingredient != null && isIngredientValid(ingredient)) {
            recipeDetail.value?.ingredients?.add(ingredient)
        }
        recipeDetail.notifyObserver()
        closeBottomSheetEvent()
    }

    private fun isIngredientValid(ingredient: Ingredient) = ingredient.name.isNotBlank()

    fun cancelIngredientDialog() {
        closeBottomSheetEvent()
    }

    private fun closeBottomSheetEvent() {
        _closeBottomSheetEvent.value = Event(Unit)
    }

    fun addPreparationStep() {
        val pos = recipeDetail.value?.preparationSteps?.size?.plus(1) ?: TODO("error")
        recipeDetail.value?.preparationSteps?.add(PreparationStep(pos=pos))
        recipeDetail.notifyObserver()

        _addedPreparationStepEvent.value = Event(Unit)
    }
}
