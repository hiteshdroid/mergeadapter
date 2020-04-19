package com.merge.adapter.sample.model.repository

import com.merge.adapter.sample.viewmodel.BaseTemplate

interface ListRepository {
    fun loadList() : MutableList<BaseTemplate>
}
