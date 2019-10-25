package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/4/8.
 */

public class BottomTabSetting implements ITabSetting {
    private float mSpace;

    @Override
    public void addTab(TabGroupView v, TabItem tabItem) {

        BottomTabView bottomTabView = new BottomTabView(v.getContext());
        bottomTabView.setTabItem(tabItem);
        bottomTabView.mSpace = mSpace;
        bottomTabView.mTextColor = v.getTextColor();
        bottomTabView.mTextCheckColor = v.getTextCheckColor();
        bottomTabView.setTextSize(v.getTextSize());
        v.addView(bottomTabView);
    }

    public BottomTabSetting space(float space) {
        mSpace = space;
        return this;
    }
}
