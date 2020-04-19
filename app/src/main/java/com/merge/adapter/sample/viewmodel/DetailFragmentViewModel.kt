package com.merge.adapter.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailFragmentViewModel : ViewModel() {
    val itemDetail = MutableLiveData<MutableList<BaseTemplate>>()

    fun loadItemDetail(listId : String) {
        itemDetail.value = getDummyItemDetail(listId)
    }

    private fun getDummyItemDetail(listId: String): MutableList<BaseTemplate> {
        return mutableListOf()
    }
}