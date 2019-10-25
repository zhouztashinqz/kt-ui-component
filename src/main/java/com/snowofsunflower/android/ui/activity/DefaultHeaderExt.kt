package com.snowofsunflower.android.ui.activity;

import android.view.View;
import android.widget.TextView
import androidx.annotation.StringRes

import com.snowofsunflower.ui_component.R;

/**
 * Created by zhouztashin on 2018/11/16.
 * 默认标题栏
 */

/**
 * 设置标题
 */
fun EnjoyActivity.setBarTitle(@StringRes id: Int) {
    mBarView?.let {
        it.findViewById<TextView>(R.id.tv_title).setText(id)
    }
}

fun EnjoyActivity.setBarLeftClickListener(listener: View.OnClickListener) {
    mBarView?.let {
        findViewById<View>(R.id.ll_left).setOnClickListener(listener)
    }
}

fun EnjoyActivity.setRightClickListener(listener: View.OnClickListener) {
    mBarView?.let {
        findViewById<View>(R.id.ll_right).setOnClickListener(listener)
    }
}

object DefaultHeaderExt {
    val layoutId: Int = R.layout.component_header_bar
    /*override var leftView: View? = null
        get() {
            if(leftView == null){
                val iv = LayoutInflater.from(mContext).inflate(R.layout.component_header_imageview,null) as ImageView
                iv.setImageResource(R.drawable.component_header_back)
                iv.setOnClickListener {
                    if(mContext is Activity){
                        mContext.finish()
                    }
                }
                leftView = iv
            }
            return leftView!!
        }
    override var rightView: View? = null
        get() {
            if(rightView == null){
                val iv = LayoutInflater.from(mContext).inflate(R.layout.component_header_imageview,null) as ImageView
                iv.visibility = View.GONE
                rightView = iv
            }
            return rightView!!
        }*/
}
