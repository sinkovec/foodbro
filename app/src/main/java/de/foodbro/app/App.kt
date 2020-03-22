package de.foodbro.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import de.foodbro.app.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
        DaggerAppComponent.factory().create(this)
}
