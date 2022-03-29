package com.sample.assignmentapp.utils

import android.content.Context
import com.sample.assignmentapp.R
import java.io.IOException

/**
 * This function returns dummy response for Restaurant list
 */
fun getResponseFromRaw(context: Context): String? {
    val jsonString: String
    try {
        jsonString = context.resources.openRawResource(R.raw.restaurants).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

/**
 * This status denotes current state of restaurant
 */
enum class OpeningStatus{
    OPEN,
    ORDER_AHEAD,
    CLOSED
}

/**
 * This status denotes sorting option for restaurant list
 */
enum class SortingOptions{
    NORMAL,
    BEST_MATCH,
    NEWEST,
    RATING_AVERAGE,
    DISTANCE,
    POPULARITY,
    AVERAGE_PRODUCT_PRICE,
    DELIVERY_COST,
    MINIMUM_COST
}
