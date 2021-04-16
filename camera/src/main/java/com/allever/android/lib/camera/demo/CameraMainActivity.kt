package com.allever.android.lib.camera.demo

import android.graphics.*
import android.os.Bundle
import com.allever.android.lib.camera.CameraProxyImpl
import com.allever.android.lib.camera.R
import com.allever.android.lib.camera.core.CameraListener
import com.allever.android.lib.camera.core.CameraManager
import com.allever.android.lib.core.app.App
import com.allever.android.lib.core.base.AbstractActivity
import com.allever.android.lib.core.log
import kotlinx.android.synthetic.main.activity_camera.*

class CameraMainActivity : AbstractActivity() {

    private val srcRect = Rect()
    private val dstRectF = RectF()
    private val paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        App.init(this)

        CameraManager.injectProxy(CameraProxyImpl())

        surfaceView.post {
            CameraManager.setPreview(surfaceView)
        }
        CameraManager.setCameraListener(object : CameraListener {
            override fun onPreview(data: ByteArray, imageFormat: Int) {
                super.onPreview(data, imageFormat)
                log("收到预览回调")
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                if (bitmap == null) {
                    log("解码失败：bitmap == null")
                    return
                }
                srcRect.right = bitmap.width
                srcRect.bottom = bitmap.height

                dstRectF.right = surfaceView.measuredWidth.toFloat()
                dstRectF.bottom = surfaceView.measuredHeight.toFloat()

                var canvas: Canvas? = null

                try {
                    canvas = surfaceView.holder.lockCanvas()

                    canvas.drawBitmap(bitmap, srcRect, dstRectF, paint)

                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        surfaceView.holder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })

        btnOpenCamera.setOnClickListener {
            CameraManager.openCamera()
        }

        btnCloseCamera.setOnClickListener {
            CameraManager.closeCamera()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CameraManager.closeCamera()
        CameraManager.release()
    }
}