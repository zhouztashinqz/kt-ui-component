package com.snowofsunflower.android.ui.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.snowofsunflower.ui_component.R;


/**
 * Created by zhouztashin on 2018/3/26.
 * 标签栏容器控件，负责处理标签的位置布局和标签选中事件处理。
 */

public class TabGroupView extends ViewGroup {

    private final SparseArray<Pair<Integer, Integer>> mTabLoc = new SparseArray<>();
    private int mCurrentSelected = -1;
    private int mLastSelected = -1;
    private TabCheckListener l;
    private ITabSetting mTabSetting;
    private float mTextSize;
    private int mTextColor;
    private int mTextCheckColor;
    private boolean isBold;//是否开启点击加粗的效果
    private int animResId = 0;

    public TabGroupView(@NonNull Context context) {
        this(context, null);
    }

    public TabGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabGroupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.TabGroupView);
        if (typedArray != null) {
            mTextSize = typedArray.getDimension(R.styleable.TabGroupView_tabTextSize, 0);
            mTextColor = typedArray.getColor(R.styleable.TabGroupView_textColor, 0);
            mTextCheckColor = typedArray.getColor(R.styleable.TabGroupView_textCheckedColor, 0);
        }
    }

    public void clearStatus() {
        mLastSelected = -1;
    }

    public int getAnimResId() {
        return animResId;
    }

    public void setAnimResId(int animResId) {
        this.animResId = animResId;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getTextCheckColor() {
        return mTextCheckColor;
    }

    public void setTabCheckListener(TabCheckListener l) {
        this.l = l;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    public void setTabSetting(ITabSetting iTabSetting) {
        mTabSetting = iTabSetting;
    }

    /**
     * 添加标签
     *
     * @param tabItem
     */
    public void addTab(TabItem tabItem) {
        if (mTabSetting == null) {
            mTabSetting = new BottomTabSetting();
        }
        mTabSetting.addTab(this, tabItem);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mTabLoc.clear();
        final int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int left = 0;
        int width = (r - l) / childCount;
        int right = width;
        int top = 0;
        int bottom = b - t;
        for (int i = 0; i < childCount; i++) {
            mTabLoc.put(i, new Pair<Integer, Integer>(left, right));
            getChildAt(i).layout(left, top, right, bottom);
            left = left + width;
            right = right + width;
        }
    }


    /**
     * TODO 添加/删除/隐藏等操作
     *
     * @param index
     */
    public void setCurrentTab(int index) {
        if (index == mLastSelected) {
            return;
        }
        if (index > getChildCount() - 1) {
            return;
        }
        if (mLastSelected > getChildCount() - 1) {
            return;
        }
        notifyTabSelected(mLastSelected, index);
        mLastSelected = index;
    }

    public AbsTabView getTabView(int id) {
        final int childCount = getChildCount();
        AbsTabView v = null;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) instanceof AbsTabView) {
                v = (AbsTabView) getChildAt(i);
                if (v.getTabItem().getId() == id) {
                    return v;
                }
            }

        }
        return null;
    }

    private void notifyTabSelected(int lastSelect, int currentSelected) {
        if (lastSelect >= 0) {
            ((AbsTabView) getChildAt(lastSelect)).isCheck = false;
            (getChildAt(lastSelect)).invalidate();
            if (isBold()) {
                ((AbsTabView) getChildAt(lastSelect)).mTextPaint.setFakeBoldText(false);//取消加粗
            }
        }
        ((AbsTabView) getChildAt(currentSelected)).isCheck = true;
        if (isBold()) {
            ((AbsTabView) getChildAt(currentSelected)).mTextPaint.setFakeBoldText(true);//加粗
        }
        startAnimation(getChildAt(currentSelected));//开启动画
        (getChildAt(currentSelected)).invalidate();
        if (l != null) {
            l.onCheck(currentSelected, (AbsTabView) getChildAt(currentSelected));
        }
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof TabGroupLayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new TabGroupLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new TabGroupLayoutParams(p);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * TODO 多点触摸，其他异常触摸
         */
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCurrentSelected = judgeWhichTab(event.getX());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //是否还在当前Tab
            if (event.getY() >= 0 && event.getY() <= getMeasuredHeight()) {
                if (mCurrentSelected >= 0 && judgeWhichTab(event.getX()) == mCurrentSelected) {
                    //选中了某个Tab
                    notifyTabSelected(mLastSelected, mCurrentSelected);
                    mLastSelected = mCurrentSelected;


                }
            }
        }
        return super.onTouchEvent(event);

    }

    private int judgeWhichTab(float x) {
        for (int i = 0; i < mTabLoc.size(); i++) {
            if (mTabLoc.get(i).first <= x && mTabLoc.get(i).second >= x) {
                return i;
            }
        }
        return -1;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // TODO 几种大小模式
        //将大小平均分给不同View
        final int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) / childCount,
                MeasureSpec.getMode(widthMeasureSpec));
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            measureChildWithMargins(child, newWidthMeasureSpec, 0, heightMeasureSpec, 0);
        }
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

    }

    private void startAnimation(View top) {
        if (animResId != 0) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), animResId);
            top.startAnimation(animation);
        }
    }

    public class TabGroupLayoutParams extends MarginLayoutParams {


        public TabGroupLayoutParams(int width, int height) {
            super(width, height);
        }

        public TabGroupLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
