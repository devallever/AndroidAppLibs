package com.everdeng.android.lib.mvvm.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface IBinder {
    /**
     *
     */
    fun bind(context: Context, owner: LifecycleOwner)
}