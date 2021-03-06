package de.foodbro.app.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import de.foodbro.app.data.converter.StringListConverter
import de.foodbro.app.data.converter.UnitsConverter
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.workers.SeedDatabaseWorker

@Database(
    entities = [Recipe::class, Ingredient::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [StringListConverter::class, UnitsConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            // delete old content
                            db.delete("recipe_table", "", emptyArray())
                            db.delete("ingredient_table", "", emptyArray())

                            // create fresh state
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}