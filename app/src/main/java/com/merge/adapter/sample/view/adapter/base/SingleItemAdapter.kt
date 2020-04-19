package com.merge.adapter.sample.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class SingleItemAdapter<T, K : BaseRecyclerViewAdapter.BaseVH> : BaseRecyclerViewAdapter<K>() {
    private var item: T? = null
    override fun getSpanSize() = 1
    fun setItem(item : T) {
        this.item = item
        notifyDataSetChanged()
    }
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
        val view = LayoutInflater.from(parent.context).inflate(getItemLayout(), parent, false)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view: View): K

    abstract fun getItemLayout(): Int

    override fun getItemCount()  : Int {
        return if (item == null) {
            0
        } else {
            1
        }
    }

    final override fun onBindViewHolder(holder: K, position: Int) {
        bindView(holder, item!!)
    }

    abstract fun bindView(holder: K, item: T)
}
