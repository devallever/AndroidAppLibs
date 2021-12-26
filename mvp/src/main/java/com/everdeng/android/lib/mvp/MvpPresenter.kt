package com.everdeng.android.lib.mvp

import com.everdeng.android.lib.core.helper.CoroutineHelper
import com.everdeng.android.lib.mvp.base.BasePresenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MvpPresenter: BasePresenter<MvpView>() {
    fun getData() {
        CoroutineHelper.mainCoroutine.launch {
            val result = "HelloHHH"
            delay(2000)
            getView()?.updateView(result)
        }
    }
}