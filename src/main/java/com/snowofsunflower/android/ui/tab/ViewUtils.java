package com.snowofsunflower.android.ui.tab;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.view.View;

/**
 * Created by zhouztashin on 2018/4/8.
 * view'draw utils.
 */

public class ViewUtils {

    /**
     * 文字的0,0坐标，其中y坐标，指的是左下角的位置。
     * get the center (x,y) of the text for draw.
     *
     * @param v         where the text draw to.
     * @param text      the text  for draw.
     * @param textPaint the paint of the text.
     * @return the center of the draw position.
     */
    public static XY getTextCenterXY(View v, String text, Paint textPaint) {
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        XY xy = new XY();
        xy.x = v.getMeasuredWidth() / 2 - rect.width() / 2;
        xy.y = v.getMeasuredHeight() / 2 + rect.height() / 2;
        return xy;

    }

    public static float getTextWidth(String text, TextPaint textPaint) {
        return Layout.getDesiredWidth(text, textPaint);
    }

    public static float getTextHeight(View v, String text, Paint textPaint) {
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();

    }

    static class XY {
        public float x;
        public float y;
    }


}
