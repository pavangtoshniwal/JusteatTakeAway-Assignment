package com.sample.assignmentapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.assignmentapp.R
import com.sample.assignmentapp.model.Restaurant

/**
 * This class is responsible for creating ViewHolder for different ViewType
 */
object ViewHolderFactory {

    /**
     * This method creates and returns required ViewHolder to be used in respective RecyclerView Adapter
     * @param view :
     *
     */
    fun create(view: View): RecyclerView.ViewHolder {
        return RestaurantViewHolder(view)
    }

    //ViewHolder required for Restaurant list
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BaseRecyclerViewAdapter.Binder<Restaurant> {

        private var restaurantName: TextView = itemView.findViewById(R.id.item_name)
        private var openState: TextView = itemView.findViewById(R.id.item_state)
        private var distance: TextView = itemView.findViewById(R.id.item_distance)
        private var rating: TextView = itemView.findViewById(R.id.item_rating)

        override fun bind(data: Restaurant) {
            restaurantName.text = data.name
            openState.text = data.status
            distance.text = data.sortingValues.distance.toString()
            rating.text = data.sortingValues.ratingAverage.toString()
        }
    }
}