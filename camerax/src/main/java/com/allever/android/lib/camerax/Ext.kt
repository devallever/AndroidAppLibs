package com.allever.android.lib.camerax

import android.util.Log

private val TAG = CameraXProxyImpl::class.java.simpleName

fun log(msg: String) {
    Log.d(TAG, msg)
}

fun loge(msg: String) {
    Log.e(TAG, msg)
}