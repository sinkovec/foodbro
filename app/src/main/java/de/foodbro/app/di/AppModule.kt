package de.foodbro.app.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.App
import de.foodbro.app.ui.detail.RecipeDetailFragment
import de.foodbro.app.ui.edit.RecipeEditFragment
import de.foodbro.app.ui.recipes.RecipesFragment


@Module
interface AppModule {

    @Binds
    fun bindApplication(app: App): Application

    @Binds
    fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    fun recipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    fun recipeDetailFragment(): RecipeDetailFragment

    @ContributesAndroidInjector
    fun recipeEditFragment(): RecipeEditFragment
}