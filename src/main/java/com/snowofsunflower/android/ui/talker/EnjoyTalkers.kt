package com.snowofsunflower.android.ui.talker

import android.app.Activity
import com.snowofsunflower.android.ui.talkerimpl.AskTalker
import com.snowofsunflower.android.ui.talkerimpl.HoldTalker
import com.snowofsunflower.android.ui.talkerimpl.PromptTalker
import com.snowofsunflower.ui_component.R

/**
 * 简易对话框
 */
class EnjoyTalkers {
    companion object {
        /**
         * 等待提示对话框
         */
        fun hold(activity: Activity): IHoldTalker {
            val holdTalker = HoldTalker(activity)
            holdTalker.about("")
            return holdTalker
        }

        /**
         * 提问对话框
         */
        // 无法先调用about方法，会导致无法调用question方法，如何处理？,通过泛型处理
        fun ask(activity: Activity): IAskTalker {
            val askTalker = AskTalker(activity)
            askTalker.about(activity.getString(R.string.talk_default_title))
            askTalker.noText(activity.getString(R.string.talk_default_no_text))
            askTalker.yesText(activity.getString(R.string.talk_default_yes_text))
            return askTalker
        }

        /**
         * 提示对话框
         */
        fun prompt(activity: Activity): IPromptTalker {
            val promptTalker = PromptTalker(activity)
            promptTalker.about(activity.getString(R.string.talk_default_title))
            promptTalker.reactText(activity.getString(R.string.talk_default_rect_text))
            return promptTalker
        }

    }
}