package com.merge.adapter.sample.view.adapter.base

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merge.adapter.sample.R
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter.BaseVH
import com.merge.adapter.sample.view.adapter.base.HeaderWithChildrenSectionAdapter.*
import com.merge.adapter.sample.view.adapter.base.layoutmanager.CustomGridLayoutManager
import com.merge.adapter.sample.view.base.CustomSpanSizeLookup

abstract class HeaderWithChildrenSectionAdapter<HeaderAdapter : BaseRecyclerViewAdapter<out BaseVH>,
                                                ChildrenAdapter : BaseRecyclerViewAdapter<out BaseVH>>
    : BaseRecyclerViewAdapter<SectionVH<HeaderAdapter, ChildrenAdapter>>() {
    abstract fun getHeaderAdapter(): HeaderAdapter

    abstract fun getChildrenAdapter(): ChildrenAdapter

    abstract fun bindDataToAdapter()

    class SectionVH<HA : BaseRecyclerViewAdapter<out BaseVH>,
            CA : BaseRecyclerViewAdapter<out BaseVH>>(
        headerAdapter: HA,
        childrenAdapter: CA,
        recyclerView: RecyclerView
    ) : BaseVH(recyclerView) {

        init {
            val mergeAdapter = MergeAdapter()
            mergeAdapter.addAdapter(headerAdapter)
            mergeAdapter.addAdapter(childrenAdapter)
            val gridLayoutManager =
                CustomGridLayoutManager(recyclerView.context, 2, RecyclerView.VERTICAL, false)
            recyclerView.layoutManager = gridLayoutManager
            gridLayoutManager.setSpanSizeLookup(mergeAdapter)
            recyclerView.adapter = mergeAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionVH<HeaderAdapter, ChildrenAdapter> {
        val recyclerView = RecyclerView(parent.context)
        recyclerView.id = R.id.header_with_children_section_list_id
        return SectionVH(getHeaderAdapter(), getChildrenAdapter(), recyclerView)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SectionVH<HeaderAdapter, ChildrenAdapter>, position: Int) {
        bindDataToAdapter()
    }
}