package com.everdeng.android.lib.mvvm.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everdeng.android.lib.core.app.App.Companion.context
import com.everdeng.android.lib.core.base.AbstractActivity
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<VM : ViewModel, DB : ViewDataBinding> : AbstractActivity() {

    protected lateinit var mBinding: DB
    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
        mViewModel =
            ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                    return modelClass.getDeclaredConstructor().newInstance()
//                    val clz = (this@BaseMvvmActivity.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
//                    return clz.getDeclaredConstructor().newInstance()
                }
            }).get((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>)
        mBinding.setVariable(getBindingVariable(), mViewModel)
        (mViewModel as BaseViewModel).bind(context, this)
        initView()
        (mViewModel as BaseViewModel).init()
    }

    override fun onDestroy() {
        super.onDestroy()
        (mViewModel as BaseViewModel).destroy()
    }

    abstract fun getLayoutId(): Int
    abstract fun getBindingVariable(): Int
    abstract fun initView()
}