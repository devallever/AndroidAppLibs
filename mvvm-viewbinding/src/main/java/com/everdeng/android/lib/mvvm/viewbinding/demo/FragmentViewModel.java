package com.everdeng.android.lib.mvvm.viewbinding.demo;

import androidx.lifecycle.MutableLiveData;

import com.everdeng.android.lib.mvvm.viewbinding.base.BaseViewModel;


/**
 * @author allever
 */
public class FragmentViewModel extends BaseViewModel {
    public MutableLiveData<String> nickName = new MutableLiveData<>();

    public void login() {
        nickName.setValue("NickName");
    }
}
