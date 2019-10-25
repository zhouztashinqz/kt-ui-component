package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/4/8.
 */

public class BottomTabItem extends TabItem {
    private int checkIcon;
    private int icon;

    public int getCheckIcon() {
        return checkIcon;
    }

    public void setCheckIcon(int checkIcon) {
        this.checkIcon = checkIcon;
    }

    public BottomTabItem checkIcon(int checkIcon) {
        this.checkIcon = checkIcon;
        return this;
    }

    public BottomTabItem icon(int icon) {
        this.icon = icon;
        return this;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
