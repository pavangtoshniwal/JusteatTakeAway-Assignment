package com.sample.assignmentapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.assignmentapp.R
import com.sample.assignmentapp.adapter.RestaurantRecyclerViewAdapter
import com.sample.assignmentapp.databinding.ActivityRestaurantsBinding
import com.sample.assignmentapp.utils.SortingOptions.*
import com.sample.assignmentapp.viewmodel.RestaurantsListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RestaurantsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityRestaurantsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RestaurantsListViewModel> { viewModelFactory }
    private lateinit var adapter: RestaurantRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
    }

    private fun initObservers() {
        viewModel.restaurantList.observe(this){
            if (it.isNotEmpty()){
                adapter = RestaurantRecyclerViewAdapter(ArrayList(it))

                with(binding) {
                    restaurantRv.layoutManager = LinearLayoutManager(this@RestaurantsActivity,
                        LinearLayoutManager.VERTICAL ,false)
                    restaurantRv.adapter = adapter
                    updateUiVisibility(true)
                }
            }else{
                binding.updateUiVisibility(false)
            }
        }

        viewModel.dataLoading.observe(this){
            binding.progressBar.isVisible = it
        }

        viewModel.getRestaurantsList(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sortingOptions = when (item.itemId){
            R.id.best_match -> BEST_MATCH
            R.id.newest -> NEWEST
            R.id.rating_average -> RATING_AVERAGE
            R.id.distance -> DISTANCE
            R.id.popularity -> POPULARITY
            R.id.average_product_price -> AVERAGE_PRODUCT_PRICE
            R.id.delivery_cost -> DELIVERY_COST
            R.id.minimum_cost -> MINIMUM_COST
            else -> NORMAL
        }
        viewModel.sortAsPerUserSelection(sortingOptions, adapter.mListItems)
        return super.onOptionsItemSelected(item)
    }

    private fun ActivityRestaurantsBinding.updateUiVisibility(isVisible:Boolean) {
        restaurantRv.isVisible = isVisible
        noResultsTv.isVisible = !isVisible
    }
}