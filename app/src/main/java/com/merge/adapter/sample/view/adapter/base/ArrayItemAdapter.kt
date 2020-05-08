package com.merge.adapter.sample.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class ArrayItemAdapter<T, K : BaseRecyclerViewAdapter.BaseVH> :
    BaseRecyclerViewAdapter<K>() {

    private var listArray : MutableList<T>? = null

    fun setItems(list : MutableList<T>) {
        this.listArray = list
        notifyDataSetChanged()
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
        val view = LayoutInflater.from(parent.context).inflate(
            getItemLayout(),
            parent,
            false)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view : View) : K

    abstract fun getItemLayout(): Int

    override fun getItemCount() = listArray?.size?:0

    final override fun onBindViewHolder(holder: K, position: Int) {
        bindView(holder, listArray!![position])
    }

    abstract fun bindView(holder: K, item : T)
}
