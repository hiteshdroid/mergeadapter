package com.merge.adapter.sample.viewmodel

sealed class ResponseStatus {
    data class Loading(val isLoading : Boolean) : ResponseStatus()
    data class Error(val message: String) : ResponseStatus()
    data class Success(val list : MutableList<BaseTemplate>) : ResponseStatus()
}
