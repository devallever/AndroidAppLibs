package com.allever.android.lib.core.helper

import android.os.Build
import android.view.View
import android.view.Window

object ScreenHelper {

    /**
     * 设置全屏显示
     */
    fun setFullScreen(window: Window) {
        // 全屏显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

}