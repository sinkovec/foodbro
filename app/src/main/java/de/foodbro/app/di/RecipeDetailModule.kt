package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.detail.RecipeDetailFragment

@Module
abstract class RecipeDetailModule {

    @ContributesAndroidInjector
    abstract fun recipeDetailFragment(): RecipeDetailFragment
}