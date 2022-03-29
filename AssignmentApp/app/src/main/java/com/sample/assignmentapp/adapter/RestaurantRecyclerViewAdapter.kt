package com.sample.assignmentapp.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sample.assignmentapp.R
import com.sample.assignmentapp.model.Restaurant

class RestaurantRecyclerViewAdapter(private var restaurantList: ArrayList<Restaurant>):
    BaseRecyclerViewAdapter<Restaurant>(restaurantList), Filterable {

    private var originalRestaurantList = ArrayList<Restaurant>()

    init {
        originalRestaurantList = restaurantList
    }

    override fun getLayoutId(position: Int, obj: Restaurant): Int {
        return R.layout.item_restaurant_list_row
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(view)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(searchQuery: CharSequence?): FilterResults {
                val filteredList = searchQuery?.let {
                    val filterList = ArrayList<Restaurant>()
                    if (it.isNotEmpty()) {
                        for (restaurant in originalRestaurantList) {
                            if (restaurant.name.contains(it, true))
                                filterList.add(restaurant)
                        }
                    }else{
                        filterList.addAll(originalRestaurantList)
                    }
                    filterList
                } ?: run {
                    originalRestaurantList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList

                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(searchQuery: CharSequence?, filterResults: FilterResults?) {
                restaurantList = filterResults?.let {
                    it.values as ArrayList<Restaurant>
                } ?: kotlin.run {
                    originalRestaurantList
                }
                mListItems = restaurantList
                notifyDataSetChanged()
            }
        }
    }


}