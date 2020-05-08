package com.merge.adapter.sample.view.adapter.base.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.merge.adapter.sample.view.base.CustomSpanSizeLookup

class CustomGridLayoutManager : GridLayoutManager {
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    constructor(context: Context?, spanCount: Int) : super(context, spanCount) {}
    constructor(
        context: Context?,
        spanCount: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, spanCount, orientation, reverseLayout) {
    }

    fun setSpanSizeLookup(adapter : MergeAdapter) {
       super.setSpanSizeLookup(CustomSpanSizeLookup(adapter))
    }
}