package de.foodbro.app.di

import android.app.Application
import android.util.Log
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import de.foodbro.app.database.*
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    db.delete("recipe_table", "", emptyArray())
                    db.delete("ingredient_table", "", emptyArray())
                    db.delete("preparations_table", "", emptyArray())

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

                    chiliConCarnePreparationContentValues(1).forEach {
                        db.insert("preparations_table", OnConflictStrategy.REPLACE, it)
                    }

                    chiliConCarnePreparationContentValues(2).forEach {
                        db.insert("preparations_table", OnConflictStrategy.REPLACE, it)
                    }

                    chiliConCarnePreparationContentValues(3).forEach {
                        db.insert("preparations_table", OnConflictStrategy.REPLACE, it)
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao = appDatabase.recipeDao()

    @Singleton
    @Provides
    fun provideIngredientDao(appDatabase: AppDatabase): IngredientDao = appDatabase.ingredientDao()

    @Singleton
    @Provides
    fun providePreparationDao(appDatabase: AppDatabase): PreparationDao = appDatabase.preparationDao()

}