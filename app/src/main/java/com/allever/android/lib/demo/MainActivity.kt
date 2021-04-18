package com.allever.android.lib.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allever.android.lib.camera.demo.CameraMainActivity
import com.allever.android.lib.camerax.demo.CameraXMainActivity
import com.allever.android.lib.core.base.AbstractActivity
import com.allever.android.lib.core.helper.ActivityHelper
import com.allever.android.lib.core.helper.HandlerHelper

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HandlerHelper.mainHandler.postDelayed(Runnable {
            ActivityHelper.startActivity(this, CameraXMainActivity::class.java)
        }, 1000)
    }
}