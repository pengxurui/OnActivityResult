package com.xurui.onresult.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.third.ThirdActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv_activity.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)

            ActivityResult.get().start(this, intent, 1) { resultCode, data ->
                if (RESULT_OK == resultCode) {
                    Toast.makeText(this, "回调SecondActivity", Toast.LENGTH_SHORT).show();
                }
            }
        }

        tv_back.setOnClickListener {
            setResult(RESULT_OK, Intent())

            finish();
        }
    }
}
