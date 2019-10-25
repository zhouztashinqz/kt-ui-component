package com.snowofsunflower.android.ui.activity

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.snowofsunflower.ui_component.R


/**
 * 带标题栏的的Activity
 */
abstract class BarActivity : BaseActivity() {

    /**
     * 没有标题栏
     */
    val mBarView: View?
        get() = findViewById(mBarLayoutId)
    protected abstract val mBarLayoutId: Int
    override fun setLayout(layoutId: Int) {
        if (mBarLayoutId != 0) {
            setRootView()
            setBarAndContentView()
        } else {
            super.setLayout(mContentLayoutId)
            if (Build.VERSION.SDK_INT >= 28) {
                val lp = window.attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.attributes = lp
            }
        }
    }

    private fun setRootView() {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        LayoutInflater.from(self).inflate(R.layout.component_activity_bar, viewGroup)
    }

    private fun setBarAndContentView() {
        val flBar = findViewById<FrameLayout>(R.id.fl_bar)
        val flContent = findViewById<FrameLayout>(R.id.fl_content)

        LayoutInflater.from(self).inflate(mBarLayoutId, flBar)
        if (mContentLayoutId != 0) {
            LayoutInflater.from(self).inflate(mContentLayoutId, flContent)
            initViewAndEvent()
        }
    }

    fun hideBar() {
        mBarView?.visibility = View.GONE
    }

    fun showBar() {
        mBarView?.visibility = View.VISIBLE
    }

    companion object {
        /**
         * 没有标题栏
         */
        val NO_BAR_LAYOUT = 0
    }

}