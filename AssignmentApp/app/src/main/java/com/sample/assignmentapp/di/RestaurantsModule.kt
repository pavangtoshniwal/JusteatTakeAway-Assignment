package com.sample.assignmentapp.di

import androidx.lifecycle.ViewModel
import com.sample.assignmentapp.view.RestaurantsActivity
import com.sample.assignmentapp.viewmodel.RestaurantsListViewModel
import com.sample.assignmentapp.viewmodel.ViewModelBuilder
import com.sample.assignmentapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


/**
 * Dagger module for the restaurants list feature.
 */
@Module
abstract class RestaurantsModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun restaurantsActivity(): RestaurantsActivity

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsListViewModel::class)
    abstract fun bindViewModel(viewModel: RestaurantsListViewModel): ViewModel
}