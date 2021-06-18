package com.everdeng.android.lib.mvp.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.everdeng.android.lib.core.base.AbstractActivity

abstract class BaseMvpActivity<V: BaseView, P: BasePresenter<V>,  DB : ViewDataBinding>: AbstractActivity(){

    protected lateinit var mPresenter: P
    protected lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
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