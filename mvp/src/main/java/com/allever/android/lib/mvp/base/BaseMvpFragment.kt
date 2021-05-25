package com.allever.android.lib.mvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allever.android.lib.core.base.AbstractFragment

abstract class BaseMvpFragment<V: BaseView, P: BasePresenter<V>> : AbstractFragment() {
    protected lateinit var mPresenter: P
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mPresenter = getPresenter()
        mPresenter.attach(this as V)
        return inflater.inflate(getLayoutId(), container);
    }

    override fun onDestroyView() {
        mPresenter.detach(this as V)
        super.onDestroyView()
    }

    protected abstract fun getPresenter(): P
    protected abstract fun getLayoutId(): Int
}