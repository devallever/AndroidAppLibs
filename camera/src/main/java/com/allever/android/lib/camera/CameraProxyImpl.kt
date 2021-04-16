package com.allever.android.lib.camera

import android.content.Context
import android.graphics.ImageFormat
import android.hardware.Camera
import android.util.Log
import android.view.Surface
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import com.allever.android.lib.camera.core.CameraListener
import com.allever.android.lib.camera.core.ICameraProxy
import java.lang.ref.WeakReference

class CameraProxyImpl : ICameraProxy {

    private var mCamera: Camera? = null

    private var mListener: CameraListener? = null

    /**
     * 相机id， 默认使用后置
     */
    private var mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK

    private lateinit var mPreviewRef: WeakReference<View>

    private val mPreviewCallback = Camera.PreviewCallback { data, camera ->
        log("onPreviewFrame")
        val format = camera?.parameters?.previewFormat
        mListener?.onPreview(data, format!!)
    }

    override fun openCamera() {
        if (mCamera != null) {
            return
        }

        //获取合适的cameraId 后置->前置
        val cameraId = getCameraId()
        openCamera(cameraId)
    }

    override fun openCamera(cameraId: Int) {
        mCameraId = cameraId
        log("打开相机：$mCameraId" )
        try {
            if (mCamera == null) {
                mCamera = Camera.open(mCameraId)
            }

            if (mCamera == null) {
                loge("打开相机失败")
                return
            }

            val camera = mCamera!!

            //获取参数
            val params = camera.parameters
            params.previewFormat = ImageFormat.NV21
            camera.parameters = params

            when (mPreviewRef.get()) {
                is SurfaceView -> {
                    camera.setPreviewDisplay((mPreviewRef.get() as SurfaceView).holder)
                }
            }

            camera.setDisplayOrientation(getDisplayOrientation(mPreviewRef.get()?.context, mCameraId))
            camera.setPreviewCallback(mPreviewCallback)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
            mListener?.onError(e.message ?: "打开相机失败")
        }
    }

    override fun closeCamera() {
        synchronized(this) {
            if (mCamera == null) {
                return
            }
            mCamera?.setPreviewCallback(null)
            mCamera?.stopPreview()
            mCamera?.release()
            mCamera = null
            log("关闭相机")
        }
    }

    override fun release() {
        mCamera = null
    }

    override fun setPreview(view: View) {
        mPreviewRef = WeakReference(view)
    }

    override fun setCameraListener(listener: CameraListener?) {
        mListener = listener
    }

    override fun getDisplayOrientation(context: Context?, cameraId: Int): Int {
        val info = Camera.CameraInfo()
        log("getDisplayOrientation: cameraId = $cameraId")
        Camera.getCameraInfo(cameraId, info)
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        val rotation = windowManager?.defaultDisplay?.rotation
        var degrees = 0
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }
        var result: Int
        //前置
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360
            result = (360 - result) % 360
        } else {
            result = (info.orientation - degrees + 360) % 360
        }//后置

        return result
    }

    private fun getCameraId(): Int {
        return Camera.CameraInfo.CAMERA_FACING_BACK;
    }

    private fun log(msg: String) {
        Log.d(CameraProxyImpl::class.java.simpleName, msg)
    }

    private fun loge(msg: String) {
        Log.e(CameraProxyImpl::class.java.simpleName, msg)
    }
}