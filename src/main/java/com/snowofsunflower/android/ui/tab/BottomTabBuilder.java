package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/4/8.
 * 底部标签栏构建器
 */

public class BottomTabBuilder extends AbsTabBuilder<BottomTabItem, BottomTabSetting> {

    public BottomTabItem newContent() {
        mCurrentTabItem = new BottomTabItem();
        return (BottomTabItem) mCurrentTabItem;
    }

    public BottomTabSetting newSetting() {
        BottomTabSetting bottomTabSetting = new BottomTabSetting();
        mTabGroupView.setTabSetting(bottomTabSetting);
        return bottomTabSetting;
    }


}
