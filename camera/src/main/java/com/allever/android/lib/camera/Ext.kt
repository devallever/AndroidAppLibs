package com.allever.android.lib.camera

import android.util.Log

private val TAG = CameraProxyImpl::class.java.simpleName

fun log(msg: String) {
    Log.d(TAG, msg)
}

fun loge(msg: String) {
    Log.e(TAG, msg)
}