package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/4/9.
 * 顶部构建构建起
 */

public class TopTapBuilder extends AbsTabBuilder<TabItem, TopTabSetting> {
    @Override
    public TabItem newContent() {
        mCurrentTabItem = new TabItem();
        return mCurrentTabItem;
    }

    @Override
    public TopTabSetting newSetting() {
        TopTabSetting tabSetting = new TopTabSetting();
        mTabGroupView.setTabSetting(tabSetting);

        return tabSetting;
    }
}
