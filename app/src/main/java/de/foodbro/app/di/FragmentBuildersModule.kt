package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.di.edit.RecipeEditFragmentBuildersModule
import de.foodbro.app.di.edit.RecipeEditViewModelModule
import de.foodbro.app.ui.detail.RecipeDetailFragment
import de.foodbro.app.ui.edit.RecipeEditFragment
import de.foodbro.app.ui.recipes.RecipesFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeRecipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): RecipeDetailFragment

    @ContributesAndroidInjector(modules = [
        RecipeEditFragmentBuildersModule::class, RecipeEditViewModelModule::class
    ])
    abstract fun contributeEditFragment(): RecipeEditFragment
}