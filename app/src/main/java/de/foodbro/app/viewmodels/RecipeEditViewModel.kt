package de.foodbro.app.viewmodels

import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import de.foodbro.app.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeEditViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted val recipeId: Int
): ViewModel() {

    val recipeDetail = recipeRepository.getRecipe(recipeId)

    fun saveRecipe() = viewModelScope.launch {
        recipeDetail.value?.let { recipeRepository.insert(it) }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(recipeId: Int): RecipeEditViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            recipeId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(recipeId) as T
            }
        }
    }
}