package com.everdeng.android.lib.mvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.everdeng.android.lib.core.base.AbstractFragment

abstract class BaseMvpFragment<V: BaseView, P: BasePresenter<V>, DB: ViewDataBinding> : AbstractFragment() {
    protected lateinit var mPresenter: P
    protected lateinit var mBinding: DB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBinding.lifecycleOwner = this
        mPresenter = getPresenter()
        mPresenter.attach(this as V)
        return mBinding.root
    }

    override fun onDestroyView() {
        mPresenter.detach(this as V)
        super.onDestroyView()
    }

    protected abstract fun getPresenter(): P
    protected abstract fun getLayoutId(): Int
}