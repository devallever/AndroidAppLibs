package com.everdeng.android.lib.core.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        fun init(context: Context) {
            this.context = context.applicationContext
        }
    }
}