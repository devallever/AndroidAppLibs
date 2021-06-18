package com.everdeng.android.lib.core.helper

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

object HandlerHelper {

    val mainHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    val subThreadHandler by lazy {
        val handlerThread = HandlerThread("")
        handlerThread.start()
        Handler(handlerThread.looper)
    }
}