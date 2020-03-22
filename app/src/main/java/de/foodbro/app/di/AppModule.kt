package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.detail.RecipeDetailFragment
import de.foodbro.app.ui.edit.RecipeEditFragment
import de.foodbro.app.ui.recipes.RecipesFragment

@Module
interface AppModule {

    @ContributesAndroidInjector
    fun recipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    fun recipeDetailFragment(): RecipeDetailFragment

    @ContributesAndroidInjector
    fun recipeEditFragment(): RecipeEditFragment
}