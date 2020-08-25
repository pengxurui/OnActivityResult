package com.xurui.onresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    companion object {
        const val KEY_EXTRA_SECOND = "key_second";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(KEY_EXTRA_SECOND, "成功啦")
            })

            finish();
        }
    }
}
