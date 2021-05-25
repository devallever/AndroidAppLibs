package com.allever.android.lib.mvp

import com.allever.android.lib.core.toast
import com.allever.android.lib.mvp.base.BaseMvpActivity

class MvpActivity: BaseMvpActivity<MvpView, MvpPresenter>(), MvpView {

    override fun getPresenter(): MvpPresenter = MvpPresenter()

    override fun getLayoutId(): Int = R.layout.activity_mvp

    override fun init() {
        mPresenter.getData()
    }

    override fun updateView(text: String) {
        toast(text)
    }
}