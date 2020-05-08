package com.merge.adapter.sample.view.adapter

import com.merge.adapter.sample.model.data.ListItem
import com.merge.adapter.sample.model.data.TitleItem
import com.merge.adapter.sample.view.adapter.base.HeaderWithChildrenSectionAdapter

class GridListSectionAdapter : HeaderWithChildrenSectionAdapter<TitleItemAdapter, GridListAdapter>() {
    private lateinit var itemList: MutableList<ListItem>
    private lateinit var titleItem: TitleItem

    private lateinit var headerAdapter: TitleItemAdapter
    private lateinit var childrenAdapter: GridListAdapter

    fun setHeader(titleItem: TitleItem) {
        this.titleItem = titleItem
    }

    fun setChildren(itemList: MutableList<ListItem>) {
        this.itemList = itemList
    }

    override fun getHeaderAdapter(): TitleItemAdapter {
        headerAdapter = TitleItemAdapter()
        return headerAdapter
    }

    override fun getChildrenAdapter(): GridListAdapter {
        childrenAdapter = GridListAdapter()
        return childrenAdapter
    }

    override fun getSpanSize() = 2

    override fun bindDataToAdapter() {
        headerAdapter.setItem(titleItem)
        childrenAdapter.setItems(itemList)
    }
}