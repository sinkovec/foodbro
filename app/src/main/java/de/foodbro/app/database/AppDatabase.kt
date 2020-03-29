package de.foodbro.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.IntPairConverter
import de.foodbro.app.database.converter.StringListConverter
import de.foodbro.app.database.converter.UnitConverter
import de.foodbro.app.database.converter.UriConverter
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Label
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe

@Database(
    entities = [Recipe::class, Ingredient::class, PreparationStep::class, Label::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [UnitConverter::class, StringListConverter::class, IntPairConverter::class, UriConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun preparationDao(): PreparationDao
}

