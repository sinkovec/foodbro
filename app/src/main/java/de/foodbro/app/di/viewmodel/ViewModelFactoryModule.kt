package de.foodbro.app.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bind(factory: ViewModelFactory): ViewModelProvider.Factory
}