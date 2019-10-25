package com.snowofsunflower.android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.snowofsunflower.ui_component.R

/**
 * 基础Activity类,沉浸式、全屏配置
 */
abstract class BaseActivity : AppCompatActivity(), UI {

    var self: BaseActivity? = null
    /**
     * 是否开启沉浸式
     */
    open val isTint = true
    /**
     * 是否全屏
     */
    open val isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        self = this
        setLayout(mContentLayoutId)
        if (isTint && !isFullScreen) {
            initTint()
        }
    }

    /**
     * 初始化沉浸式
     */
    private fun initTint() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .statusBarColor(R.color.transparent)
            .fitsSystemWindows(true)
            .init()
    }

    /**
     * 设置并初始化页面
     */
    open fun setLayout(@LayoutRes layoutId: Int) {
        if (layoutId != 0) {
            setContentView(layoutId)
            initViewAndEvent()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (isFullScreen && hasFocus) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

}