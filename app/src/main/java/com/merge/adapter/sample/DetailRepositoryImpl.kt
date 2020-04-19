package com.merge.adapter.sample

import android.content.Context
import com.merge.adapter.sample.model.repository.ItemRepository
import com.merge.adapter.sample.model.repository.ListRepository
import com.merge.adapter.sample.viewmodel.BaseTemplate

class DetailRepositoryImpl(applicationContext: Context) : ItemRepository {
    override fun loadItem(): MutableList<BaseTemplate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}