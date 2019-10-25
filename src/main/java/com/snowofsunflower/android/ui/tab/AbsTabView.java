package com.snowofsunflower.android.ui.tab;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by zhouztashin on 2018/4/8.
 * 标签抽象类
 */

public abstract class AbsTabView extends View {
    /**
     * 标签内容
     */
    protected TabItem mTabItem;
    protected TextPaint mTextPaint;
    /**
     * 是否选中
     */
    boolean isCheck;
    int mTextColor;
    int mTextCheckColor;

    public AbsTabView(Context context) {
        super(context);
    }

    public AbsTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
    }


    /**
     * 获取标签内容
     *
     * @return
     */
    public TabItem getTabItem() {
        return mTabItem;
    }

    /**
     * 设置标签内容
     *
     * @param tabItem
     */
    public void setTabItem(TabItem tabItem) {
        mTabItem = tabItem;
    }
}
