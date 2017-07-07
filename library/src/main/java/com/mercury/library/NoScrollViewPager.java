package com.mercury.library;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 创建者:    wang.zhonghao
 * 创建时间:  2016/10/13
 * 描述:      禁止滑动
 */
public class NoScrollViewPager extends ViewPager {

    //默认是可以滑动的
    private boolean isScroll = true;


    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

}
