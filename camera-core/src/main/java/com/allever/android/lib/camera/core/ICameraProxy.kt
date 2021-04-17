package com.allever.android.lib.camera.core

import android.content.Context
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

    fun takePicture()

    /**
     * 设置监听
     */
    fun setCameraListener(listener: CameraListener?)

    /**
     * 设置旋转
     */
    fun getDisplayOrientation(context: Context?, cameraId: Int): Int

    /**
     * 获取支持的预览大小
     */
    fun getSupportPreviewSize(): MutableList<Size>

    /**
     * 获取支持的图片大小
     */
    fun getSupportPictureSize(): MutableList<Size>

}