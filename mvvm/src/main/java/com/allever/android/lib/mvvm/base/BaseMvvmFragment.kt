package com.allever.android.lib.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allever.android.lib.core.base.AbstractFragment
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmFragment<VM : ViewModel, DB : ViewDataBinding> : AbstractFragment() {

    protected lateinit var mBinding: DB
    protected lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBinding.lifecycleOwner = this
        mViewModel =
            ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                    val clz = (activity?.javaClass?.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
                    return clz.getDeclaredConstructor().newInstance()
                }
            }).get((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>)
        mBinding.setVariable(getBindingVariable(), mViewModel)
        (mViewModel as BaseViewModel).bind(requireContext(), this)
        initView()
        (mViewModel as BaseViewModel).init()
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (mViewModel as BaseViewModel).destroy()
    }

    abstract fun getLayoutId(): Int
    abstract fun getBindingVariable(): Int
    abstract fun initView()

}