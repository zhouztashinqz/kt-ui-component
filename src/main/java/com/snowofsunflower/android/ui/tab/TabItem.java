package com.snowofsunflower.android.ui.tab;

/**
 * Created by zhouztashin on 2018/3/26.
 * 基础标签栏内容
 */

public class TabItem {

    private String text;
    private int id;


    public int getId() {
        return id;
    }

    ;

    public void setId(int id) {
        this.id = id;
    }

    public TabItem id(int id) {
        this.id = id;
        return this;
    }

    public TabItem text(String text) {
        this.text = text;
        return this;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TabItem{" +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
