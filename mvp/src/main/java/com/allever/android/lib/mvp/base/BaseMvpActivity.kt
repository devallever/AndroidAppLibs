package com.allever.android.lib.mvp.base

import android.os.Bundle
import com.allever.android.lib.core.base.AbstractActivity

abstract class BaseMvpActivity<V: BaseView, P: BasePresenter<V>>: AbstractActivity(){

    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPresenter = getPresenter()
        mPresenter.attach(this as V)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach(this as V)
    }

    abstract fun getLayoutId(): Int
    abstract fun getPresenter(): P
    abstract fun init()
}