package com.allever.android.lib.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allever.android.lib.core.base.AbstractActivity

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}