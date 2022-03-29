package com.sample.assignmentapp.repository

import android.content.Context
import com.sample.assignmentapp.model.Restaurant

interface RestaurantsRepository {

    suspend fun getRestaurants(context: Context): Result<List<Restaurant>>
}