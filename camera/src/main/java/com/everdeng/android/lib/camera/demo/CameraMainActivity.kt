package com.everdeng.android.lib.camera.demo

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.everdeng.android.lib.camera.CameraProxyImpl
import com.everdeng.android.lib.camera.R
import com.everdeng.android.lib.camera.core.CameraListener
import com.everdeng.android.lib.camera.core.CameraManager
import com.everdeng.android.lib.camera.log
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

class CameraMainActivity : AppCompatActivity() {

    private val srcRect = Rect()
    private val dstRectF = RectF()
    private val paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        CameraManager.init(this)

        CameraManager.injectProxy(CameraProxyImpl())

        surfaceView.post {
            CameraManager.setPreview(surfaceView)
        }
        CameraManager.setCameraListener(object : CameraListener {
            override fun onPreview(data: ByteArray, imageFormat: Int) {
            }

            override fun onTakePicture(data: ByteArray?, bitmap: Bitmap?, imageFormat: Int) {
                val path = this@CameraMainActivity.externalCacheDir?.absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
                val result = CameraManager.saveBitmap2File(
                    bitmap,
                    path
                )
                if (result) {
                    log("保存成功：$path")
                } else {
                    log("保存失败")
                }
            }
        })

        btnOpenFrontCamera.setOnClickListener {
            CameraManager.openCamera(1)
        }

        btnOpenCamera.setOnClickListener {
            CameraManager.openCamera()
        }

        btnCloseCamera.setOnClickListener {
            CameraManager.closeCamera()
        }

        btnTackPicture.setOnClickListener {
            CameraManager.takePicture()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CameraManager.closeCamera()
        CameraManager.release()
    }
}