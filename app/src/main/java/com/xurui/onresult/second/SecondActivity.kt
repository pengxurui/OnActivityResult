package com.xurui.onresult.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.xurui.onresult.ActivityIntentLauncher
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.third.ThirdActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv_activity.setOnClickListener {
            val launcher = object : ActivityIntentLauncher {

                override fun createIntent() = Intent(this@SecondActivity, ThirdActivity::class.java)

                override fun onActivityResult(resultCode: Int, data: Intent?) {
                    if (RESULT_OK == resultCode) {
                        Toast.makeText(this@SecondActivity, "back to SecondActivity", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            ActivityResult.with(this).start(launcher);
        }

        tv_back.setOnClickListener {
            setResult(RESULT_OK, Intent())

            finish();
        }
    }
}
