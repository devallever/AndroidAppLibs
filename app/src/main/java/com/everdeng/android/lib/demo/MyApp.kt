package com.everdeng.android.lib.demo

import com.everdeng.android.lib.core.app.App

class MyApp : App() {
    override fun onCreate() {
        super.onCreate()
        App.init(this)
    }
}