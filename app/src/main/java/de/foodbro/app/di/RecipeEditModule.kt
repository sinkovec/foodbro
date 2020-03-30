package de.foodbro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.foodbro.app.ui.edit.RecipeEditIngredientFragment
import de.foodbro.app.ui.edit.RecipeEditPreparationFragment
import de.foodbro.app.ui.edit.RecipeEditSummaryFragment
import de.foodbro.app.ui.edit.dialogs.DurationPickerDialogFragment
import de.foodbro.app.ui.edit.dialogs.UnitsDialogFragment

@Module
interface RecipeEditModule {

    @ContributesAndroidInjector //(modules = [RecipeEditSummaryModule::class])
    fun editSummaryFragment(): RecipeEditSummaryFragment

    @ContributesAndroidInjector //(modules = [RecipeEditIngredientModule::class])
    fun editIngredientsFragment(): RecipeEditIngredientFragment

    @ContributesAndroidInjector
    fun editPreparationFragment(): RecipeEditPreparationFragment

//    @Module
//    interface RecipeEditSummaryModule {
//        @ContributesAndroidInjector
//        fun durationPickerDialogFragment(): DurationPickerDialogFragment
//    }
//
//    @Module
//    interface RecipeEditIngredientModule {
//        @ContributesAndroidInjector
//        fun unitsDialogFragment(): UnitsDialogFragment
//    }
}