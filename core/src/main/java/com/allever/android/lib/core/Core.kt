package com.allever.android.lib.core

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import com.allever.android.lib.core.app.App
import com.allever.android.lib.core.helper.HandlerHelper

private const val TAG = "ILogger"

fun log(msg: String) {
    log(TAG, msg)
}

fun log(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun toast(resId: Int) {
    toast(getString(resId))
}

fun toast(msg: String) {
    HandlerHelper.mainHandler.post {
        Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
    }
}

fun getString(@StringRes resId: Int): String {
    return App.context.resources.getString(resId)
}