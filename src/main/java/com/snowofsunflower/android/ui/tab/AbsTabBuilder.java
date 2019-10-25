package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/4/8.
 * Function: 抽象标签构建器。
 */

public abstract class AbsTabBuilder<T extends TabItem, S extends ITabSetting> {

    protected TabItem mCurrentTabItem;
    protected TabGroupView mTabGroupView;

    /**
     * 创建新标签项
     *
     * @return
     */
    public abstract T newContent();

    /**
     * 创建新的标签设置
     *
     * @return
     */
    public abstract S newSetting();

    /**
     * 将当前创建的标签项目添加到标签容器中
     */
    public TabItem add() {
        mTabGroupView.addTab(mCurrentTabItem);
        return mCurrentTabItem;
    }

    public void groupView(TabGroupView tabGroupView) {
        this.mTabGroupView = tabGroupView;
    }
}
