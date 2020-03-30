package de.foodbro.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.foodbro.app.ui.edit.RecipeEditViewModel

@Module
interface ViewModelModule {
    @Binds
    fun bind(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecipeEditViewModel::class)
    fun bindRecipeEditViewModel(viewModel: RecipeEditViewModel): ViewModel
}