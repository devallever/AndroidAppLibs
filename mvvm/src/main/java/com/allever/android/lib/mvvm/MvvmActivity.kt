package com.allever.android.lib.mvvm

import com.allever.android.lib.mvvm.base.BaseMvvmActivity
import com.allever.android.lib.mvvm.databinding.ActivityMvvmBinding

class MvvmActivity : BaseMvvmActivity<MvvmViewModel, ActivityMvvmBinding>(){
    override fun getLayoutId(): Int = R.layout.activity_mvvm

    override fun getBindingVariable(): Int = BR.mvvmViewModel

    override fun initView() {

    }
}