package com.merge.adapter.sample.view.adapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<K : BaseRecyclerViewAdapter.BaseVH> : RecyclerView.Adapter<K>() {
    open fun getSpanSize() = 1

    open class BaseVH(view : View) : RecyclerView.ViewHolder(view)
}