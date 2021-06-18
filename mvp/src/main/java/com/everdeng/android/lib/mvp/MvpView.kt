package com.everdeng.android.lib.mvp

import com.everdeng.android.lib.mvp.base.BaseView

interface MvpView : BaseView {
    fun updateView(text: String)
}