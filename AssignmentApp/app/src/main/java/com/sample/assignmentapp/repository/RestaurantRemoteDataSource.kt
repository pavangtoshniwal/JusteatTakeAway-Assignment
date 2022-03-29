package com.sample.assignmentapp.repository

import android.content.Context
import com.google.gson.Gson
import com.sample.assignmentapp.model.Restaurant
import com.sample.assignmentapp.model.RestaurantsResponse
import com.sample.assignmentapp.utils.getResponseFromRaw
import kotlinx.coroutines.delay

/**
 * This class is used to make remote calls for requested functionality
 */
object RestaurantRemoteDataSource: RestaurantsDataSource {

    private lateinit var restaurantList: List<Restaurant>
    private const val NETWORK_DELAY = 3000L

    /**
     * This function reads .json file from res/raw folder and returns Result object with restaurant list
     */
    override suspend fun getRestaurants(context: Context): Result<List<Restaurant>> {
        val response = getResponseFromRaw(context)
        val restaurantResponse = Gson().fromJson(response, RestaurantsResponse::class.java)
        restaurantList = restaurantResponse.restaurants
        //Added delay to simulate network call
        delay(NETWORK_DELAY)
        return Result.Success(restaurantList)
    }
}