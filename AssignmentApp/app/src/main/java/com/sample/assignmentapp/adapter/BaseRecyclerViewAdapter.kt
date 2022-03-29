package com.sample.assignmentapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This class is used to create Adapter as per required
 */
abstract class BaseRecyclerViewAdapter<T>(listItems: List<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mListItems: List<T> = listItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
            , viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(mListItems[position])
    }

    override fun getItemCount(): Int {
        return mListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, mListItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder

    /**
     * Interface that needs to be implemented by respective ViewHolder classes
     * to bind the view items for that respective viewHolder
     */
    internal interface Binder<T> {
        fun bind(data: T)
    }
}