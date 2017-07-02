package com.wzh.customviewpager.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Mercury on 2017/7/2.
 */

public class BannerViewPager extends ViewPager {

    private BannerAdapter mBannerAdapter;
    private int[] imageResIds;
    private Context mContext;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 将广告条显示到下一个
                    int currentItem = BannerViewPager.this.getCurrentItem();
                    BannerViewPager.this.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    break;

                default:
                    break;
            }
        }
    };

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setBannerAdapter(int[] ids) {
        this.imageResIds = ids;
        mBannerAdapter = new BannerAdapter();
        this.setAdapter(mBannerAdapter);
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 停止自动轮播
                mHandler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, 2000);
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public class BannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int pos = position % imageResIds.length;
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(imageResIds[pos]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }

}
