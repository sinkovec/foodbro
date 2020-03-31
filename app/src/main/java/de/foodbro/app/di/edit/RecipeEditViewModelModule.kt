package de.foodbro.app.di.edit

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.foodbro.app.di.viewmodel.ViewModelKey
import de.foodbro.app.ui.edit.RecipeEditViewModel

@Module
abstract class RecipeEditViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeEditViewModel::class)
    abstract fun bind(viewModel: RecipeEditViewModel): ViewModel
}