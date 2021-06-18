package com.everdeng.android.lib.demo

import android.os.Bundle
import com.everdeng.android.lib.core.base.AbstractActivity
import com.everdeng.android.lib.core.helper.ActivityHelper
import com.everdeng.android.lib.core.helper.CoroutineHelper
import com.everdeng.android.lib.core.helper.HandlerHelper
import com.everdeng.android.lib.core.log
import com.everdeng.android.lib.mvp.MvpActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HandlerHelper.mainHandler.postDelayed({
            ActivityHelper.startActivity(MvpActivity::class.java)
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