package com.merge.adapter.sample

import android.app.Application

class MergeAdapterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        InfraProvider.init(this)
    }
}