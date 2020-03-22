package de.foodbro.app.di

import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import de.foodbro.app.App
import de.foodbro.app.database.*
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(app: App): AppDatabase {
        return Room.inMemoryDatabaseBuilder(app, AppDatabase::class.java)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    db.delete("recipe_table", "", emptyArray())
                    db.delete("ingredient_table", "", emptyArray())

                    db.insert("recipe_table", OnConflictStrategy.REPLACE, chiliConCarneContentValues())
                    db.insert("recipe_table", OnConflictStrategy.REPLACE, chiliConCarneContentValues())
                    db.insert("recipe_table", OnConflictStrategy.REPLACE, chiliConCarneContentValues())

                    chiliConCarneIngredientsContentValues(1).forEach {
                        db.insert("ingredient_table", OnConflictStrategy.REPLACE, it)
                    }

                    chiliConCarneIngredientsContentValues(2).forEach {
                        db.insert("ingredient_table", OnConflictStrategy.REPLACE, it)
                    }

                    chiliConCarneIngredientsContentValues(3).forEach {
                        db.insert("ingredient_table", OnConflictStrategy.REPLACE, it)
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }

    @Singleton
    @Provides
    fun providesIngredientDao(appDatabase: AppDatabase): IngredientDao {
        return appDatabase.ingredientDao()
    }

}