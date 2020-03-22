package de.foodbro.app.ui.edit

import androidx.lifecycle.ViewModel
import de.foodbro.app.repository.IngredientRepository
import de.foodbro.app.repository.RecipeRepository
import javax.inject.Inject

class RecipeEditViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository) : ViewModel() {

    fun setup(recipeId: Int?) {
        if (recipeId == null) {
            // No need to populate, it's a new recipe
            return
        }


    }
}
