package com.snowofsunflower.android.ui.activity

/**
 * Created by zhouztashin on 2018/11/13.
 */

interface UI {

    /**
     *布局文件ID
     */
    val mContentLayoutId: Int


    /**
     * 初始化控件与事件
     */
    fun initViewAndEvent()
}
