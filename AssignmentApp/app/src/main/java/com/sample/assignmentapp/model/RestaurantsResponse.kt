package com.sample.assignmentapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantsResponse (
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>
)