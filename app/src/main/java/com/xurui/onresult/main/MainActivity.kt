package com.xurui.onresult.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xurui.onresult.ActivityIntentLauncher
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_activity.setOnClickListener {
            val launcher = object : ActivityIntentLauncher {

                override fun createIntent() = Intent(this@MainActivity, SecondActivity::class.java)

                override fun onActivityResult(resultCode: Int, data: Intent?) {
                    if (Activity.RESULT_OK == resultCode) {
                        Toast.makeText(this@MainActivity, "back to MainActivity", Toast.LENGTH_SHORT)
                            .show();
                    }
                }
            }

            ActivityResult.with(this).start(launcher)
        }
    }
}
