package com.sample.assignmentapp.model

import com.google.gson.annotations.SerializedName
import com.sample.assignmentapp.utils.OpeningStatus


data class Restaurant(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("status")
    val status:String,
    @SerializedName("sortingValues")
    val sortingValues:SortingValues,

    var openingStatus: OpeningStatus
)

data class SortingValues(
    @SerializedName("bestMatch")
    val bestMatch:Double,
    @SerializedName("newest")
    val newest:Double,
    @SerializedName("ratingAverage")
    val ratingAverage:Double,
    @SerializedName("distance")
    val distance:Int,
    @SerializedName("popularity")
    val popularity:Double,
    @SerializedName("averageProductPrice")
    val averageProductPrice:Int,
    @SerializedName("deliveryCosts")
    val deliveryCosts:Int,
    @SerializedName("minCost")
    val minCost:Int
)
