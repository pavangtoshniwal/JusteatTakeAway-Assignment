package com.sample.assignmentapp.di

import com.sample.assignmentapp.repository.DefaultRestaurantsRepository
import com.sample.assignmentapp.repository.RestaurantRemoteDataSource
import com.sample.assignmentapp.repository.RestaurantsDataSource
import com.sample.assignmentapp.repository.RestaurantsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideTasksRemoteDataSource(): RestaurantsDataSource {
        return RestaurantRemoteDataSource
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultRestaurantsRepository): RestaurantsRepository
}
