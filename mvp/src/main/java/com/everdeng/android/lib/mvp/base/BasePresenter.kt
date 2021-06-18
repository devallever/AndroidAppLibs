package com.everdeng.android.lib.mvp.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

abstract class BasePresenter<V: BaseView> {

    protected lateinit var mViewRef: Reference<V>

    fun attach(v: V) {
        mViewRef = WeakReference(v)
    }

    fun detach(v: V) {
        mViewRef.clear()
    }

    fun getView(): V {
        return mViewRef.get() as V
    }

}