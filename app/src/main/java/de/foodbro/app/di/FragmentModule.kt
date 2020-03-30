package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.detail.RecipeDetailFragment
import de.foodbro.app.ui.edit.RecipeEditFragment
import de.foodbro.app.ui.recipes.RecipesFragment

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun contributeRecipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    fun contributeDetailFragment(): RecipeDetailFragment

    @ContributesAndroidInjector(modules = [RecipeEditModule::class])
    fun contributeEditFragment(): RecipeEditFragment
}