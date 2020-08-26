package com.xurui.onresult.second

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.base.BaseFragment
import com.xurui.onresult.third.ThirdActivity

/**
 * Created by pengxr on 2020/8/26.
 */
class SecondInnerFragment :BaseFragment() {

    override fun getText() = "SecondInnerFragment"

    override fun onClick() {
        val intent = Intent(activity, ThirdActivity::class.java)

        ActivityResult.get().start(this, intent, 3) { resultCode, data ->
            if (Activity.RESULT_OK == resultCode) {
                Toast.makeText(activity, "回调SecondInnerFragment", Toast.LENGTH_SHORT).show();
            }
        }
    }
}