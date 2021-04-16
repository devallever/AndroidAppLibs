package com.allever.android.lib.demo

import com.allever.android.lib.core.app.App

class MyApp : App() {
    override fun onCreate() {
        super.onCreate()
        App.init(this)
    }
}