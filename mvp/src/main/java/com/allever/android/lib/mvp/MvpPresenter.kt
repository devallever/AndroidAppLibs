package com.allever.android.lib.mvp

import com.allever.android.lib.core.helper.CoroutineHelper
import com.allever.android.lib.mvp.base.BasePresenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MvpPresenter: BasePresenter<MvpView>() {
    fun getData() {
        CoroutineHelper.mainCoroutine.launch {
            val result = "HelloHHH"
            delay(2000)
            getView().updateView(result)
        }
    }
}