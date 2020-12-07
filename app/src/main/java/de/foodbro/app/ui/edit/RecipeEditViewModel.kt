package de.foodbro.app.ui.edit

import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import de.foodbro.app.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeEditViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted val recipeId: Long
): ViewModel() {

    val recipeDetail = recipeRepository.getRecipe(recipeId)

    fun saveRecipe() = viewModelScope.launch {
        recipeDetail.value?.let { recipeRepository.insert(it) }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(recipeId: Long): RecipeEditViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            recipeId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(recipeId) as T
            }
        }
    }
}