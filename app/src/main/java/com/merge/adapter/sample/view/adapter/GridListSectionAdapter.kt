package com.merge.adapter.sample.view.adapter

import android.view.View
import com.merge.adapter.sample.R
import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.model.data.TitleItem
import com.merge.adapter.sample.view.adapter.base.BaseSectionAdapter

class GridListSectionAdapter : BaseSectionAdapter<
        TitleItemAdapter,
        GridListAdapter,
        GridListSectionAdapter.GridSectionVH>() {

    inner class GridSectionVH(val view: View) : SectionVH<TitleItemAdapter, GridListAdapter>(view) {
        private lateinit var headerAdapter: TitleItemAdapter
        private lateinit var childrenAdapter: GridListAdapter

        override fun getHeaderAdapter(): TitleItemAdapter {
            headerAdapter = TitleItemAdapter()
            return headerAdapter
        }

        override fun getChildrenAdapter(): GridListAdapter {
            childrenAdapter = GridListAdapter()
            return childrenAdapter
        }

        override fun bindHeaderAdapter() {
            headerAdapter.setItem(titleItem)
        }

        override fun bindChildrenAdapter() {
            childrenAdapter.setItems(itemList)
        }
    }

    private lateinit var itemList: MutableList<ListItem>
    private lateinit var titleItem: TitleItem

    fun setHeader(titleItem: TitleItem) {
        this.titleItem = titleItem
    }

    fun setChildren(itemList: MutableList<ListItem>) {
        this.itemList = itemList
    }

    override fun getSpanSize() = 2

    override fun getSectionLayout() = R.layout.section_layout

    override fun createSectionViewHolder(view: View) = GridSectionVH(view)
}