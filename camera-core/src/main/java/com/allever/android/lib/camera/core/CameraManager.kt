package com.allever.android.lib.camera.core

import android.view.View

object CameraManager : ICameraProxy {

    private lateinit var cameraProxy: ICameraProxy

    fun injectProxy(cameraProxy: ICameraProxy) {
        this.cameraProxy = cameraProxy
    }

    override fun openCamera() {
        cameraProxy.openCamera()
    }

    override fun openCamera(cameraId: Int) {
        cameraProxy.openCamera(cameraId)
    }

    override fun closeCamera() {
        cameraProxy.closeCamera()
    }

    override fun release() {
        cameraProxy.release()
    }

    override fun setPreview(view: View) {
        cameraProxy.setPreview(view)
    }

    override fun setCameraListener(listener: CameraListener?) {
        cameraProxy.setCameraListener(listener)
    }
}