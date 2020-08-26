package com.xurui.onresult.base

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xurui.onresult.R
import kotlinx.android.synthetic.main.layout_tv.*

/**
 * Created by pengxr on 2020/8/26.
 */
abstract class BaseFragment : Fragment() {

    abstract fun getText(): String

    abstract fun onClick()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_tv, container, false)
        root.setOnClickListener {
            onClick()
        }
        root.findViewById<TextView>(R.id.tv).text = getText()
        return root
    }

}