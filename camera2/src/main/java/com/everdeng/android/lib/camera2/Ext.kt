package com.everdeng.android.lib.camera2

import android.util.Log

private val TAG = Camera2ProxyImpl::class.java.simpleName

fun log(msg: String) {
    Log.d(TAG, msg)
}

fun loge(msg: String) {
    Log.e(TAG, msg)
}