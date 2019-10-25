package com.snowofsunflower.android.ui.activity;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by zhouztashin on 2018/11/13.
 * 基础Fragment
 */

abstract class BaseFragment : Fragment(), UI {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mContentLayoutId != 0) {
            val view = inflater.inflate(mContentLayoutId, container, false)
            initViewAndEvent()
            return view
        }
        return null
    }
}
