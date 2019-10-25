package com.snowofsunflower.android.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * 启动Activity
 */
fun Activity.toActivity(target: Class<*>) {
    val i = Intent(this, target)
    startActivity(i)
}

/**
 * 启动Activity
 */
fun Fragment.toActivity(target: Class<*>) {
    activity?.let {
        val i = Intent(activity, target)
        activity!!.startActivity(i)
    }

}
