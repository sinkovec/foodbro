package de.foodbro.app.di.edit

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.edit.RecipeEditIngredientFragment
import de.foodbro.app.ui.edit.RecipeEditPreparationFragment
import de.foodbro.app.ui.edit.RecipeEditSummaryFragment

@Module
abstract class RecipeEditFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun editSummaryFragment(): RecipeEditSummaryFragment

    @ContributesAndroidInjector
    abstract fun editIngredientsFragment(): RecipeEditIngredientFragment

    @ContributesAndroidInjector
    abstract fun editPreparationFragment(): RecipeEditPreparationFragment
}