package de.foodbro.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import de.foodbro.app.repository.RecipeRepository

class RecipeDetailViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted val recipeId: Int
): ViewModel() {

    val recipeDetail = recipeRepository.getRecipe(recipeId)

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(recipeId: Int): RecipeDetailViewModel
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