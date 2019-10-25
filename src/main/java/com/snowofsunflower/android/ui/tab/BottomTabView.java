package com.snowofsunflower.android.ui.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * Created by zhouztashin on 2018/3/26.
 * 底部标签栏
 */

public class BottomTabView extends AbsTabView {
    float mSpace;
    private Paint mBitmapPaint;
    private Paint mReadPointPaint;
    private int mRedPointRadius = 10;
    private int mRedPointColor = Color.RED;
    private boolean mShowRedPoint = false;

    public BottomTabView(Context context) {
        super(context);
        init();
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setRedPointRadius(int pxSize) {
        mRedPointRadius = pxSize;
    }

    public void setRedPointColor(int resColor) {
        mRedPointRadius = resColor;
        if (mReadPointPaint != null) {
            mReadPointPaint.setColor(resColor);
        }
    }

    public void isNeedRedPoint(boolean b) {
        mShowRedPoint = b;
        invalidate();
    }

    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
    }

    private void init() {
        mBitmapPaint = new Paint();
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(15);
        mReadPointPaint = new Paint();
        mReadPointPaint.setColor(mRedPointColor);
        mReadPointPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTabItem == null) return;
        if (!(mTabItem instanceof BottomTabItem)) return;
        BottomTabItem tabItem = (BottomTabItem) mTabItem;
        if (tabItem.getIcon() <= 0) return;
        //画图标
        BitmapDrawable drawable;
        int textColor = 0;
        if (!isCheck) {
            textColor = mTextColor;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = (BitmapDrawable) getContext().getDrawable(tabItem.getIcon());
            } else {
                drawable = (BitmapDrawable) getContext().getResources().getDrawable(tabItem.getIcon());
            }
        } else {
            textColor = mTextCheckColor;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = (BitmapDrawable) getContext().getDrawable(tabItem.getCheckIcon());
            } else {
                drawable = (BitmapDrawable) getContext().getResources().getDrawable(tabItem.getCheckIcon());
            }
        }
        //画文字
        final String text = tabItem.getText();
        Rect rect = new Rect();
        mTextPaint.setColor(textColor);
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        final int textX = getMeasuredWidth() / 2 - rect.width() / 2;
        final int iconY = (int) (getMeasuredHeight() / 2 - (drawable.getBitmap().getHeight() / 2 + rect.height() / 2 + mSpace / 2));
        final int iconX = getMeasuredWidth() / 2 - (drawable.getBitmap().getWidth() / 2);
        canvas.drawBitmap(drawable.getBitmap(), iconX, iconY, mBitmapPaint);
        canvas.drawText(text, textX, iconY + rect.height() + mSpace + drawable.getBitmap().getHeight(), mTextPaint);
        //绘制红点
        if (mShowRedPoint) {
            canvas.drawCircle(iconX + drawable.getBitmap().getWidth(), iconY - 5, mRedPointRadius, mReadPointPaint);
        }
    }
}
