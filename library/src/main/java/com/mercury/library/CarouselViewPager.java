package com.mercury.library;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mercury on 2017/7/2.
 * 以viewpager实现轮播图的效果
 */

public class CarouselViewPager extends ViewPager {

    private BannerAdapter mBannerAdapter;
    private int[] imageResIds;
    private Context mContext;
    //用来记录上一个点的位置
    private int prePosition = 0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 将广告条显示到下一个
                    int currentItem = CarouselViewPager.this.getCurrentItem();
                    CarouselViewPager.this.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    break;

                default:
                    break;
            }
        }
    };

    public CarouselViewPager(Context context) {
        super(context);
        mContext = context;
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    protected void initListener(final String[] description, final TextView textView, final LinearLayout llpointGroup) {
        //设置页面监听
        this.addOnPageChangeListener(new OnPageChangeListener() {
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

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        int top = rect.top;
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