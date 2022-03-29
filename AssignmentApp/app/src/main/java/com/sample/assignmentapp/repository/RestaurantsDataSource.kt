package com.sample.assignmentapp.repository

import android.content.Context
import com.sample.assignmentapp.model.Restaurant

interface RestaurantsDataSource {

    suspend fun getRestaurants(context: Context): Result<List<Restaurant>>
}