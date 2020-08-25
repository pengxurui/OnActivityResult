package com.xurui.onresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xurui.onresult.SecondActivity.Companion.KEY_EXTRA_SECOND
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val CODE_REQUEST_SECOND = 1;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            ActivityResult.get().start(this, intent, CODE_REQUEST_SECOND) { resultCode, data ->
                if (Activity.RESULT_OK == resultCode) {
                    Toast.makeText(this, data.getStringExtra(KEY_EXTRA_SECOND), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    fun onActivityResultFromSecond(){

    }
}
