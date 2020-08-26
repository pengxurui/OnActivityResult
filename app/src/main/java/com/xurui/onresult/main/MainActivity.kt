package com.xurui.onresult.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_activity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            ActivityResult.get().start(this, intent, 1) { resultCode, data ->
                if (Activity.RESULT_OK == resultCode) {
                    Toast.makeText(this, "回调MainActivity", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
