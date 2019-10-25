package com.snowofsunflower.android.ui.talker;

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import com.snowofsunflower.ui_component.R

/**
 * Created by zhouztashin on 2018/11/28.
 * 大对话框
 */

open class BigTalker(activity: Activity) :
    AbsTalker(activity, R.layout.component_full_screen_dialog) {
    override fun about(str: String): AbsTalker {
        //DO NOTING
        return this
    }

    fun setContent(layoutId: Int): View {
        mView!!.let {
            return LayoutInflater.from(mActivity).inflate(layoutId, it)
        }
    }

    fun cancelable(flag: Boolean) = mDialog.setCancelable(flag)

}
