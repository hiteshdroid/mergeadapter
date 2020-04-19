package com.merge.adapter.sample.viewmodel

import InfraProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class ListFragmentViewModel : ViewModel() {
    val responseLiveData = liveData {
        emit(ResponseStatus.Loading(true))
        try {
            emit(ResponseStatus.Success(getDummyList()))
        } catch (ioException: Exception) {
            emit(ResponseStatus.Error("list Load error"))
        }
    }

    @Throws(EmptyListException::class)
    private fun getDummyList(): MutableList<BaseTemplate>{
        return InfraProvider.getListRepository().loadList()
    }

    class EmptyListException : Exception()
}