package com.allever.android.lib.mvvm

import androidx.databinding.ObservableField
import com.allever.android.lib.mvvm.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MvvmViewModel : BaseViewModel() {

    val test =  ObservableField<String>()

    override fun init() {
        test.set("Hello World")

        mCoroutine.launch {
            delay(2000)
            test.set("hhhhhhhhhh")
        }
    }
}