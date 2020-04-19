package com.merge.adapter.sample.model.repository

import com.merge.adapter.sample.viewmodel.BaseTemplate

interface ItemRepository {
    fun loadItem() : MutableList<BaseTemplate>
}
