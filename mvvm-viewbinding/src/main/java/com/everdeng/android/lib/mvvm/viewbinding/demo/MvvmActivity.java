package com.everdeng.android.lib.mvvm.viewbinding.demo;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.everdeng.android.lib.mvvm.viewbinding.base.BaseMvvmActivity;
import com.everdeng.android.lib.mvvm.viewbinding.databinding.ActivityMvvmBinding;

/**
 * @author allever
 */
public class MvvmActivity extends BaseMvvmActivity<ActivityMvvmBinding, MvvmViewModel> {

    @Override
    protected ActivityMvvmBinding bindView(LayoutInflater inflater) {
        return ActivityMvvmBinding.inflate(inflater);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.login();
    }

    @Override
    protected void observeData() {
        mViewModel.nickName.observe(this, s -> {
            mBinding.tvNickName.setText(s);
        });
    }
}
