package com.snowofsunflower.android.ui.tab;


import androidx.annotation.ColorRes;

/**
 * Created by zhouztashin on 2018/4/9.
 * 顶部标签栏设置
 */

public class TopTabSetting implements ITabSetting {
    private float mLineHeight;
    private int mLineColor;

    @Override
    public void addTab(TabGroupView v, TabItem tabItem) {
        TopTabView topTabView = new TopTabView(v.getContext());
        topTabView.mTabItem = tabItem;
        topTabView.setTextSize(v.getTextSize());
        topTabView.mTextCheckColor = v.getTextCheckColor();
        topTabView.mTextColor = v.getTextColor();
        topTabView.mLineHeight = mLineHeight;
        topTabView.mLineColor = mLineColor;
        v.addView(topTabView);
    }

    public TopTabSetting lineColor(@ColorRes int color) {
        mLineColor = color;
        return this;
    }

    public TopTabSetting lineHeight(float lineHeight) {
        mLineHeight = lineHeight;
        return this;
    }
}
