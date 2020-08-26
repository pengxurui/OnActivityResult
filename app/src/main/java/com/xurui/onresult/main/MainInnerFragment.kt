package com.xurui.onresult.main

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.base.BaseSupportFragment
import com.xurui.onresult.second.SecondActivity

/**
 * Created by pengxr on 2020/8/26.
 */
class MainInnerFragment : BaseSupportFragment() {

    override fun getText() = "MainInnerFragment"

    override fun onClick() {
        val intent = Intent(context, SecondActivity::class.java)

        ActivityResult.get().start(this, intent, 3) { resultCode, data ->
            if (Activity.RESULT_OK == resultCode) {
                Toast.makeText(context, "回调MainInnerFragment", Toast.LENGTH_SHORT).show();
            }
        }
    }
}