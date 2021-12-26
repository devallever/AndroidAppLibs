package com.everdeng.android.lib.mvvm.viewbinding.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.everdeng.android.lib.core.base.AbstractActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author allever
 */
public abstract class BaseMvvmActivity<VB extends ViewBinding, VM extends ViewModel> extends AbstractActivity {

    protected VB mBinding;
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = bindView(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            modelClass = BaseViewModel.class;
        }

        mViewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                try {
                    return modelClass.getDeclaredConstructor().newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }).get(modelClass);

        observeData();
    }


    /**
     * @return
     */
    protected abstract VB bindView(LayoutInflater inflater);


    /**
     *
     */
    protected abstract void observeData();


}
