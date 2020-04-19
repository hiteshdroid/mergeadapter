package com.merge.adapter.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merge.adapter.sample.model.data.Screen

class ActivityViewModel : ViewModel() {
    val screenLiveData = MutableLiveData<Screen>()

    fun loadScreen(screen : Screen) {
        screenLiveData.value = screen
    }
}