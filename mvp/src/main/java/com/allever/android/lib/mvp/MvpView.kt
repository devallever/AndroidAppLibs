package com.allever.android.lib.mvp

import com.allever.android.lib.mvp.base.BaseView

interface MvpView : BaseView {
    fun updateView(text: String)
}