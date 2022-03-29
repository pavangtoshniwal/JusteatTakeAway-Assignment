package com.sample.assignmentapp.di

import android.content.Context
import com.sample.assignmentapp.RestaurantsApp
import com.sample.assignmentapp.viewmodel.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelBuilder::class,
    RestaurantsModule::class])
interface ApplicationComponent : AndroidInjector<RestaurantsApp>{

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}