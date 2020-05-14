package com.merge.adapter.sample.view.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merge.adapter.sample.R
import com.merge.adapter.sample.view.adapter.base.BaseRecyclerViewAdapter.BaseVH
import com.merge.adapter.sample.view.adapter.base.HeaderWithChildrenSectionAdapter.SectionVH
import com.merge.adapter.sample.view.adapter.base.layoutmanager.CustomGridLayoutManager

abstract class HeaderWithChildrenSectionAdapter<
        HeaderAdapter : BaseRecyclerViewAdapter<out BaseVH>,
        ChildrenAdapter : BaseRecyclerViewAdapter<out BaseVH>,
        SectionViewHolder : SectionVH<HeaderAdapter, ChildrenAdapter>>
    : BaseRecyclerViewAdapter<SectionViewHolder>() {

    abstract class SectionVH<
            HeaderAdapter : BaseRecyclerViewAdapter<out BaseVH>,
            ChildrenAdapter : BaseRecyclerViewAdapter<out BaseVH>>(
            view: View
    ) : BaseVH(view) {

        abstract fun getHeaderAdapter(): HeaderAdapter
        abstract fun getChildrenAdapter(): ChildrenAdapter

        open fun getSectionRecyclerView(): RecyclerView {
            return itemView.findViewById(R.id.recyclerView)
        }

        abstract fun bindHeaderAdapter()
        abstract fun bindChildrenAdapter()

        private val mergeAdapter: MergeAdapter = MergeAdapter()

        init {
            mergeAdapter.addAdapter(getHeaderAdapter())
            mergeAdapter.addAdapter(getChildrenAdapter())
            val recyclerView = getSectionRecyclerView()
            val gridLayoutManager =
                CustomGridLayoutManager(recyclerView.context, 2, RecyclerView.VERTICAL, false)
            gridLayoutManager.setSpanSizeLookup(mergeAdapter)
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.adapter = mergeAdapter
        }
    }

    private lateinit var sectionViewHolder: SectionViewHolder

    abstract fun getSectionLayout(): Int

    override fun getItemCount() = 1

    abstract fun createSectionViewHolder(view: View): SectionViewHolder

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getSectionLayout(), null)
        sectionViewHolder = createSectionViewHolder(view)
        return sectionViewHolder
    }

    final override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        sectionViewHolder.bindHeaderAdapter()
        sectionViewHolder.bindChildrenAdapter()
    }

    fun getViewHolder() = sectionViewHolder
}