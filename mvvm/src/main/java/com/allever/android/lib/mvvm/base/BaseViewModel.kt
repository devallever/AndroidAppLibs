package com.allever.android.lib.mvvm.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel() : ViewModel(), IBinder {
    private val mJob by lazy {
        Job()
    }

    protected val mCoroutine by lazy {
        CoroutineScope(Dispatchers.Main + mJob)
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    lateinit var owner: LifecycleOwner

    abstract fun init()

    override fun bind(context: Context, owner: LifecycleOwner) {
        this.context = context
        this.owner = owner
    }

    open fun destroy() {
        mJob.cancel()
    }
}