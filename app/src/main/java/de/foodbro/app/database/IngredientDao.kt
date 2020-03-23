package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table")
    fun getAll(): LiveData<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE recipeId = :id")
    fun getAllByRecipeId(id: Int): LiveData<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ingredients: List<Ingredient>)

    fun insertAllForRecipe(recipe: Recipe, ingredients: List<Ingredient>) {
        ingredients.forEach {
            it.recipeId = recipe.id
        }
        insertAll(ingredients)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredient: Ingredient)

    @Query("DELETE FROM ingredient_table")
    fun deleteAll()

    @Query("DELETE FROM ingredient_table WHERE recipeId = :id")
    fun deleteAllByRecipeId(id: Int)

    @Delete
    fun delete(ingredient: Ingredient)
}