package com.allever.android.lib.mvp

import com.allever.android.lib.mvp.base.BaseMvpActivity
import com.allever.android.lib.mvp.databinding.ActivityMvpBinding

class MvpActivity: BaseMvpActivity<MvpView, MvpPresenter, ActivityMvpBinding>(), MvpView {

    override fun getPresenter(): MvpPresenter = MvpPresenter()

    override fun getLayoutId(): Int = R.layout.activity_mvp

    override fun init() {
        mPresenter.getData()
    }

    override fun updateView(text: String) {
        mBinding.tvHello.text = (text)
    }
}