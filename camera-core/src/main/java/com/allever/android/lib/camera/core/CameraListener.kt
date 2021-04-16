package com.allever.android.lib.camera.core

import android.graphics.ImageFormat

interface CameraListener {
    /**
     * 预览相机
     */
    fun onPreview(data: ByteArray, imageFormat: Int) {}

    fun onError(msg: String) {}
}