package de.foodbro.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import de.foodbro.app.repository.RecipeRepository

class RecipeDetailViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted val recipeId: Long
): ViewModel() {

    val recipeDetail = recipeRepository.getRecipe(recipeId)

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(recipeId: Long): RecipeDetailViewModel
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