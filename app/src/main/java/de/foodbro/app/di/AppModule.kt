package de.foodbro.app.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.App
import de.foodbro.app.ui.detail.RecipeDetailFragment
import de.foodbro.app.ui.edit.RecipeEditFragment
import de.foodbro.app.ui.edit.RecipeEditIngredientFragment
import de.foodbro.app.ui.edit.RecipeEditPreparationFragment
import de.foodbro.app.ui.edit.RecipeEditSummaryFragment
import de.foodbro.app.ui.recipes.RecipesFragment
import javax.inject.Provider
import javax.inject.Singleton


@Module
interface AppModule {

    @Binds
    fun bindApplication(app: App): Application

    @Binds
    fun bindContext(app: App): Context
}