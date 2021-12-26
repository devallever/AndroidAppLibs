package com.everdeng.android.lib.mvvm.viewbinding.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.everdeng.android.lib.mvvm.viewbinding.base.BaseMvvmFragment;
import com.everdeng.android.lib.mvvm.viewbinding.databinding.FragmentMvvmBinding;


/**
 * @author allever
 */
public class MvvmFragment extends BaseMvvmFragment<FragmentMvvmBinding, FragmentViewModel> {
    @Override
    protected FragmentMvvmBinding bindView(LayoutInflater inflater) {
        return FragmentMvvmBinding.inflate(inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.login();
        return root;
    }

    @Override
    protected void observeData() {
        mViewModel.nickName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.tvNickName.setText(s);
            }
        });
    }
}
