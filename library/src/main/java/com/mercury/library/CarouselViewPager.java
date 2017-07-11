package com.mercury.library;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mercury on 2017/7/2.
 * 以viewpager实现轮播图的效果
 */

public class CarouselViewPager extends ViewPager {

    Rect rect = new Rect();

    private int[] imageResIds;
    private String[] urls;
    private Context mContext;
    //用来记录上一个点的位置
    private int prePosition = 0;

    private int delayTime;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 轮播图显示下一个
                    int currentItem = CarouselViewPager.this.getCurrentItem();
                    CarouselViewPager.this.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(0, delayTime);
                    break;

                default:
                    break;
            }
            return false;
        }
    });

    public CarouselViewPager(Context context) {
        super(context);
        mContext = context;
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public CarouselViewPager(Context context, int delayTime) {
        this(context);
        this.delayTime = delayTime;

    }

    protected void addBannerIdListener(final String[] description, final TextView textView, final LinearLayout llpointGroup) {
        //设置页面监听
        this.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            /**
             * 轮播到该页面时调用该方法
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int pos = position % imageResIds.length;
                //先把前一个点设置为未选中
                Log.i("page", "pos:" + pos + "\tpre:" + prePosition);
                llpointGroup.getChildAt(prePosition).setSelected(false);
                prePosition = pos;
                llpointGroup.getChildAt(pos).setSelected(true);
                if (description != null && description.length == imageResIds.length) {
                    textView.setText(description[pos]);
                }
            }

        });
    }

    protected void addBannerUrlListener(final String[] description, final TextView textView, final LinearLayout llpointGroup) {
        //设置页面监听
        this.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            /**
             * 轮播到该页面时调用该方法
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int pos = position % urls.length;
                //先把前一个点设置为未选中
                Log.i("page", "pos:" + pos + "\tpre:" + prePosition);
                llpointGroup.getChildAt(prePosition).setSelected(false);
                prePosition = pos;
                llpointGroup.getChildAt(pos).setSelected(true);
                if (description != null && description.length == urls.length) {
                    textView.setText(description[pos]);
                }
            }

        });
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        getWindowVisibleDisplayFrame(rect);
        int top = rect.top;
    }


    public void setBannerAdapter(@DrawableRes int[] ids) {
        this.imageResIds = ids;
        BannerIdAdapter bannerIdAdapter = new BannerIdAdapter(mContext, ids);
        this.setAdapter(bannerIdAdapter);
        mHandler.sendEmptyMessageDelayed(0, delayTime);
    }

    public void setUrlAdapter(String[] urls, UrlBanner urlBanner) {
        this.urls = urls;
        BannerUrlAdapter bannerUrlAdapter = new BannerUrlAdapter(mContext, urls, urlBanner);
        this.setAdapter(bannerUrlAdapter);
        mHandler.sendEmptyMessageDelayed(0, delayTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 停止自动轮播
                mHandler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, delayTime);
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


}
