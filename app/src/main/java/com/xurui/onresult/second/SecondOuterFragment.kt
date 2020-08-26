package com.xurui.onresult.second

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.third.ThirdActivity
import kotlinx.android.synthetic.main.fragment_second_outer.*

/**
 * Created by pengxr on 2020/8/26.
 */
class SecondOuterFragment:Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second_outer, container, false)
        root.findViewById<TextView>(R.id.tv_fragment).setOnClickListener {
            val intent = Intent(activity, ThirdActivity::class.java)

            ActivityResult.get().start(tv_fragment, intent, 2) { resultCode, data ->
                if (Activity.RESULT_OK == resultCode) {
                    Toast.makeText(activity, "回调SecondOuterFragment", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return root
    }
}