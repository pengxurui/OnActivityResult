package com.xurui.onresult.main

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.xurui.onresult.ActivityIntentLauncher
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.base.BaseSupportFragment
import com.xurui.onresult.second.SecondActivity

/**
 * Created by pengxr on 2020/8/26.
 */
class MainInnerFragment : BaseSupportFragment() {

    override fun getText() = "MainInnerFragment"

    override fun onClick() {

        val launcher = object : ActivityIntentLauncher {

            override fun createIntent() = Intent(context, SecondActivity::class.java)

            override fun onActivityResult(resultCode: Int, data: Intent?) {
                if (Activity.RESULT_OK == resultCode) {
                    Toast.makeText(context, "back to MainInnerFragment", Toast.LENGTH_SHORT).show();
                }
            }
        }

        ActivityResult.with(this).start(launcher)
    }
}