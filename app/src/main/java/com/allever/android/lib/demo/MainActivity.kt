package com.allever.android.lib.demo

import android.os.Bundle
import com.allever.android.lib.camera.demo.CameraMainActivity
import com.allever.android.lib.camera2.demo.Camera2MainActivity
import com.allever.android.lib.camerax.demo.CameraXMainActivity
import com.allever.android.lib.core.base.AbstractActivity
import com.allever.android.lib.core.helper.ActivityHelper
import com.allever.android.lib.core.helper.CoroutineHelper
import com.allever.android.lib.core.helper.HandlerHelper
import com.allever.android.lib.core.log
import com.allever.android.lib.mvvm.MvvmActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HandlerHelper.mainHandler.postDelayed({
            ActivityHelper.startActivity(MvvmActivity::class.java)
        }, 1000)

        log("main thread id = ${android.os.Process.myTid()}")

        CoroutineHelper.mainCoroutine.launch {
            log("mainCoroutine thread Id = ${android.os.Process.myTid()}")
            val fileValue = testCoroutine()
            log("fileValue = $fileValue")
        }

    }

    private suspend fun testCoroutine(): String =
        withContext(Dispatchers.IO) {
            log("test thread id = ${android.os.Process.myTid()}")
            return@withContext "Hello"
        }

}