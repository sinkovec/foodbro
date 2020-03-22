package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.recipes.RecipesFragment

@Module
abstract class RecipesModule {

    @ContributesAndroidInjector
    internal abstract fun recipesFragment(): RecipesFragment
}