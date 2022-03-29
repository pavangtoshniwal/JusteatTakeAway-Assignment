package com.sample.assignmentapp.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.assignmentapp.model.Restaurant
import com.sample.assignmentapp.model.SortingValues
import com.sample.assignmentapp.repository.RestaurantsRepository
import com.sample.assignmentapp.repository.Result
import com.sample.assignmentapp.utils.MainCoroutineRule
import com.sample.assignmentapp.utils.OpeningStatus
import com.sample.assignmentapp.utils.SortingOptions
import com.sample.assignmentapp.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*

@ExperimentalCoroutinesApi
class RestaurantsListViewModelTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RestaurantsListViewModel
    private lateinit var repository: RestaurantsRepository
    private lateinit var context: Context

    @Before
    fun setup(){
        context = mockk()
        repository = mockk()
        viewModel = RestaurantsListViewModel(repository)
    }

    @Test
    fun testGetRestaurantsListSuccessEmptyList() {
        mainCoroutineRule.runTest {
            coEvery { repository.getRestaurants(context) } returns Result.Success(emptyList())
            viewModel.getRestaurantsList(context)
            Assert.assertEquals(viewModel.restaurantList.getOrAwaitValue().isEmpty(), true)
        }
    }

    @Test
    fun testGetRestaurantsListSuccessWithResponse() {
        mainCoroutineRule.runTest {
            coEvery { repository.getRestaurants(context) } returns Result.Success(getMockRestaurantList())
            viewModel.getRestaurantsList(context)
            Assert.assertEquals(viewModel.restaurantList.getOrAwaitValue().isNotEmpty(), true)
        }
    }

    @Test
    fun testGetRestaurantsListErrorEmptyList() {
        mainCoroutineRule.runTest {
            coEvery { repository.getRestaurants(context) } returns Result.Error(Exception("Test Error"))
            viewModel.getRestaurantsList(context)
            Assert.assertEquals(viewModel.restaurantList.getOrAwaitValue().isEmpty(), true)
        }
    }

    @Test
    fun testSortAsPerUserSelectionBestMatch() {
        viewModel.sortAsPerUserSelection(SortingOptions.BEST_MATCH, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "4")
        Assert.assertEquals(restaurantList[0].name, "Sushi One")
    }

    @Test
    fun testSortAsPerUserSelectionNewest() {
        viewModel.sortAsPerUserSelection(SortingOptions.NEWEST, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "4")
        Assert.assertEquals(restaurantList[0].name, "Sushi One")
    }

    @Test
    fun testSortAsPerUserSelectionRatingAverage() {
        viewModel.sortAsPerUserSelection(SortingOptions.RATING_AVERAGE, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "1")
        Assert.assertEquals(restaurantList[0].name, "Tanoshii Sushi")
    }

    @Test
    fun testSortAsPerUserSelectionDistance() {
        viewModel.sortAsPerUserSelection(SortingOptions.DISTANCE, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "1")
        Assert.assertEquals(restaurantList[0].name, "Tanoshii Sushi")
    }

    @Test
    fun testSortAsPerUserSelectionPopularity() {
        viewModel.sortAsPerUserSelection(SortingOptions.POPULARITY, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "4")
        Assert.assertEquals(restaurantList[0].name, "Sushi One")
    }

    @Test
    fun testSortAsPerUserSelectionAverageProductPrice() {
        viewModel.sortAsPerUserSelection(SortingOptions.AVERAGE_PRODUCT_PRICE, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "4")
        Assert.assertEquals(restaurantList[0].name, "Sushi One")
    }

    @Test
    fun testSortAsPerUserSelectionDeliveryCosts() {
        viewModel.sortAsPerUserSelection(SortingOptions.DELIVERY_COST, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "4")
        Assert.assertEquals(restaurantList[0].name, "Sushi One")
    }

    @Test
    fun testSortAsPerUserSelectionMinimumCost() {
        viewModel.sortAsPerUserSelection(SortingOptions.MINIMUM_COST, getMockRestaurantList())
        val restaurantList = viewModel.restaurantList.getOrAwaitValue()
        Assert.assertEquals(restaurantList[0].id, "1")
        Assert.assertEquals(restaurantList[0].name, "Tanoshii Sushi")
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    private fun getMockRestaurantList(): List<Restaurant>{
        return listOf(
            Restaurant("1","Tanoshii Sushi", "open",
                SortingValues(0.0, 96.0, 4.5,
                    1190, 17.0, 1536, 200,
                    1000), OpeningStatus.OPEN),
            Restaurant("2","Tandoori Express", "closed",
                SortingValues(1.0, 266.0, 4.5,
                    2308, 123.0, 1146, 150,
                    1300), OpeningStatus.CLOSED),
            Restaurant("3","Royal Thai", "order ahead",
                SortingValues( 2.0, 133.0, 4.5,
                    2639, 44.0, 1492, 150,
                    2500), OpeningStatus.ORDER_AHEAD),
            Restaurant("4","Sushi One", "open",
                SortingValues(3.0, 238.0, 4.0,
                    1618, 23.0, 1285, 0,
                    1200), OpeningStatus.OPEN),
        )
    }
}