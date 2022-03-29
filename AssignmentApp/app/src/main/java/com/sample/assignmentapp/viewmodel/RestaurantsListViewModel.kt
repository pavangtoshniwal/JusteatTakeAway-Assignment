package com.sample.assignmentapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.assignmentapp.model.Restaurant
import com.sample.assignmentapp.repository.RestaurantsRepository
import com.sample.assignmentapp.repository.Result
import com.sample.assignmentapp.utils.OpeningStatus.OPEN
import com.sample.assignmentapp.utils.OpeningStatus.CLOSED
import com.sample.assignmentapp.utils.OpeningStatus.ORDER_AHEAD
import com.sample.assignmentapp.utils.SortingOptions
import com.sample.assignmentapp.utils.SortingOptions.BEST_MATCH
import com.sample.assignmentapp.utils.SortingOptions.NEWEST
import com.sample.assignmentapp.utils.SortingOptions.DISTANCE
import com.sample.assignmentapp.utils.SortingOptions.RATING_AVERAGE
import com.sample.assignmentapp.utils.SortingOptions.POPULARITY
import com.sample.assignmentapp.utils.SortingOptions.AVERAGE_PRODUCT_PRICE
import com.sample.assignmentapp.utils.SortingOptions.DELIVERY_COST
import com.sample.assignmentapp.utils.SortingOptions.MINIMUM_COST
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class RestaurantsListViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository) : ViewModel() {

    private val _restaurantList = MutableLiveData<List<Restaurant>>()
    val restaurantList: LiveData<List<Restaurant>> = _restaurantList

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getRestaurantsList(context: Context){
        _dataLoading.value = true

        viewModelScope.launch {
            val restaurantListResult = restaurantsRepository.getRestaurants(context)

            if (restaurantListResult is Result.Success){
                val restaurants = sortRestaurantsByOpeningStatus(restaurantListResult.data)
                _restaurantList.value = restaurants
            }else{
                _restaurantList.value = emptyList()
            }
            _dataLoading.value = false
        }
    }

    private fun sortRestaurantsByOpeningStatus(restaurantList: List<Restaurant>): List<Restaurant>{
        for (i in restaurantList.indices){
            when(restaurantList[i].status.lowercase(Locale.getDefault())){
                "open" -> restaurantList[i].openingStatus = OPEN
                "closed" -> restaurantList[i].openingStatus = CLOSED
                else -> restaurantList[i].openingStatus = ORDER_AHEAD
            }
        }

        return restaurantList.sortedBy { it.openingStatus.ordinal }
    }

    fun sortAsPerUserSelection(sortingOptions: SortingOptions, restaurantList: List<Restaurant>){
        _dataLoading.value = true
        val sortedRestaurantList: List<Restaurant>
        when(sortingOptions){
            BEST_MATCH ->{
                //this will sort only on the basis of Sorting Options values
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.bestMatch }.reversed()
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{-it.sortingValues.bestMatch }))
            }
            NEWEST->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.newest }.reversed()
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{-it.sortingValues.newest }))
            }
            RATING_AVERAGE->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.ratingAverage }.reversed()
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{-it.sortingValues.ratingAverage }))
            }
            DISTANCE->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.distance }
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{it.sortingValues.distance }))
            }
            POPULARITY->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.popularity }.reversed()
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{-it.sortingValues.popularity }))
            }
            AVERAGE_PRODUCT_PRICE->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.averageProductPrice }
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{it.sortingValues.averageProductPrice }))
            }
            DELIVERY_COST->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.deliveryCosts }
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{it.sortingValues.deliveryCosts }))
            }
            MINIMUM_COST->{
                //sortedRestaurantList = restaurantList.sortedBy { it.sortingValues.minCost }
                sortedRestaurantList = restaurantList.sortedWith(compareBy({it.openingStatus},{it.sortingValues.minCost }))
            }
            else -> {
                sortedRestaurantList = restaurantList.sortedBy { it.openingStatus.ordinal }
            }
        }

        _restaurantList.value = sortedRestaurantList
        _dataLoading.value = false
    }
}
