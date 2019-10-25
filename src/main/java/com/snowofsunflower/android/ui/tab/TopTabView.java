package com.snowofsunflower.android.ui.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;


/**
 * Created by zhouztashin on 2018/3/26.
 * 顶部标签栏
 */

public class TopTabView extends AbsTabView {

    float mLineHeight;
    int mLineColor;
    private Paint mLinePaint;

    public TopTabView(Context context) {
        super(context);
        init();
    }

    public TopTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public TopTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(15);
        mLineHeight = 10;
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // if (mTabItem == null || mTabItem.getIcon() <= 0) return;
        int textColor = 0;
        if (!isCheck) {
            textColor = mTextColor;

        } else {
            textColor = mTextCheckColor;
        }
        //画文字
        final String text = mTabItem.getText();
        mTextPaint.setColor(textColor);
        ViewUtils.XY xy = ViewUtils.getTextCenterXY(this, text, mTextPaint);
        canvas.drawText(text, xy.x, xy.y, mTextPaint);
        if (isCheck) {
            if (mLineColor != 0) {
                mLinePaint.setColor(getContext().getResources().getColor(mLineColor));
            } else {
                mLinePaint.setColor(textColor);
            }
            mLinePaint.setStrokeWidth(mLineHeight);
            //画线
            float lineStartx = xy.x;
            float lineEndx = lineStartx + ViewUtils.getTextWidth(text, mTextPaint);
            float lineEndY = getMeasuredHeight();
            canvas.drawLine(lineStartx, lineEndY, lineEndx, lineEndY, mLinePaint);

        }


    }
}