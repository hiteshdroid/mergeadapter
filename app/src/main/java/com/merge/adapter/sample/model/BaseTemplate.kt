package com.merge.adapter.sample.viewmodel

import com.merge.adapter.sample.model.data.ListItem

sealed class BaseTemplate {
    data class HorizontalList(val itemList: MutableList<ListItem>) : BaseTemplate()
    data class VerticalList(val itemList: MutableList<ListItem>) : BaseTemplate()
    data class GridList(val itemList: MutableList<ListItem>) : BaseTemplate()
    data class SingleItem(val item: ListItem) : BaseTemplate()
}
