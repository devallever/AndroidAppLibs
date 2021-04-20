package com.allever.android.lib.camerax.demo

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allever.android.lib.camera.core.CameraFacing
import com.allever.android.lib.camera.core.CameraListener
import com.allever.android.lib.camera.core.CameraManager
import com.allever.android.lib.camerax.CameraXProxyImpl
import com.allever.android.lib.camerax.R
import com.allever.android.lib.camerax.log
import kotlinx.android.synthetic.main.activity_camerax.*
import java.io.File

class CameraXMainActivity : AppCompatActivity() {

    private val srcRect = Rect()
    private val dstRectF = RectF()
    private val paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camerax)


        CameraManager.injectProxy(CameraXProxyImpl())

        previewView.post {
            CameraManager.setPreview(previewView)
            CameraManager.setLifeCycleOwner(this)
        }
        CameraManager.setCameraListener(object : CameraListener {
            override fun onPreview(data: ByteArray, imageFormat: Int) {
            }

            override fun onTakePicture(data: ByteArray?, bitmap: Bitmap?, imageFormat: Int) {
                val path = this@CameraXMainActivity.externalCacheDir?.absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
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
            CameraManager.openCamera(CameraFacing.FACE_FRONT)
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