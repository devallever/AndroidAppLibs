package com.allever.android.lib.camera.core

import android.view.View

interface ICameraProxy {

    /**
     * 打开相机
     */
    fun openCamera()

    /**
     * 打开指定相机
     * @param cameraId 相机Id
     */
    fun openCamera(cameraId: Int)

    /**
     * 关闭相机
     */
    fun closeCamera()

    /**
     * 释放资源
     */
    fun release()

    /**
     * 设置预览View
     */
    fun setPreview(view: View)

    /**
     * 设置监听
     */
    fun setCameraListener(listener: CameraListener?)

}