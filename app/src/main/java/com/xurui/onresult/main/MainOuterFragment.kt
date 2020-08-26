package com.xurui.onresult.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xurui.onresult.ActivityIntentLauncher
import com.xurui.onresult.ActivityResult
import com.xurui.onresult.R
import com.xurui.onresult.second.SecondActivity
import kotlinx.android.synthetic.main.fragment_main_outer.*

/**
 * Created by pengxr on 2020/8/26.
 */
class MainOuterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_outer, container, false)
        root.findViewById<TextView>(R.id.tv_fragment).setOnClickListener {

            val launcher = object : ActivityIntentLauncher {

                override fun createIntent() = Intent(context, SecondActivity::class.java)

                override fun onActivityResult(resultCode: Int, data: Intent?) {
                    if (Activity.RESULT_OK == resultCode) {
                        Toast.makeText(context, "back to MainOuterFragment", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            ActivityResult.with(tv_fragment).start(launcher);
        }
        return root
    }
}