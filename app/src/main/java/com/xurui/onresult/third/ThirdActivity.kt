package com.xurui.onresult.third

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.xurui.onresult.R
import kotlinx.android.synthetic.main.activity_third.*

/**
 * Created by pengxr on 2020/8/26.
 */
class ThirdActivity :Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        tv_back.setOnClickListener {
            setResult(RESULT_OK, Intent())

            finish();
        }
    }
}