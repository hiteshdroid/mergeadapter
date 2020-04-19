package com.merge.adapter.sample.view.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter.BaseVH

class CustomSpanSizeLookup(val adapter : MergeAdapter) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        val adapterByItemPosition =
            adapter.getAdapterByItemPosition(position)
        return adapterByItemPosition?.getSpanSize() ?: 1
    }
}

private fun MergeAdapter.getAdapterByItemPosition(position: Int) :
        BaseRecyclerViewAdapter<BaseVH>? {
    var actualPosition = position
    var adapterByItemPosition : BaseRecyclerViewAdapter<BaseVH>? = null
    for (i in 0 until adapters.size) {
        val adapter = adapters[i]
        val itemCount = adapter.itemCount
        if (actualPosition < itemCount) {
            adapterByItemPosition = adapter
                    as BaseRecyclerViewAdapter<BaseVH>?
            break
        } else {
            actualPosition -= itemCount
        }
    }
    return adapterByItemPosition
}
