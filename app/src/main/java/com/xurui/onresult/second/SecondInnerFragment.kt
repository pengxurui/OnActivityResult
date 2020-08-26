package com.xurui.onresult.second

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.xurui.onresult.ActivityIntentLauncher
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.base.BaseFragment
import com.xurui.onresult.third.ThirdActivity

/**
 * Created by pengxr on 2020/8/26.
 */
class SecondInnerFragment :BaseFragment() {

    override fun getText() = "SecondInnerFragment"

    override fun onClick() {
        val launcher = object : ActivityIntentLauncher {

            override fun createIntent() = Intent(activity, ThirdActivity::class.java)

            override fun onActivityResult(resultCode: Int, data: Intent?) {
                if (Activity.RESULT_OK == resultCode) {
                    Toast.makeText(activity, "back to SecondInnerFragment", Toast.LENGTH_SHORT).show();
                }
            }
        }

        ActivityResult.with(this).start(launcher)
    }
}