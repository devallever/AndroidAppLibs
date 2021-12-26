package com.everdeng.android.lib.mvp

import com.everdeng.android.lib.mvp.base.BaseMvpFragment
import com.everdeng.android.lib.mvp.databinding.FragmentMvpBinding

class MvpFragment : BaseMvpFragment<MvpView, MvpPresenter, FragmentMvpBinding>() , MvpView{

    override fun getPresenter(): MvpPresenter = MvpPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_mvp

    override fun updateView(text: String) {

    }
}