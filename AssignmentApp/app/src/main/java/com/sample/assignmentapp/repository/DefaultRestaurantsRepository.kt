package com.sample.assignmentapp.repository

import android.content.Context
import com.sample.assignmentapp.model.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRestaurantsRepository@Inject constructor(
    private val restaurantsDataSource: RestaurantsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantsRepository {

    override suspend fun getRestaurants(context: Context): Result<List<Restaurant>> {
        return withContext(ioDispatcher){
            restaurantsDataSource.getRestaurants(context)
        }
    }

}