package com.wzh.customviewpager.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Mercury on 2017/7/2.
 * 自定义控件，以viewpager实现轮播图的效果
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

        LinearLayout linearLayout = new LinearLayout(mContext);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
//                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER_VERTICAL;
//        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, this.getId());
        linearLayout.setLayoutParams(layoutParams);
        TextView textView = new TextView(mContext);
        textView.setText("放在控件里面");
        textView.setTextColor(Color.parseColor("#ff0000"));
        linearLayout.addView(textView);
        this.addView(linearLayout);

        for (int i = 0; i < imageResIds.length; i++) {

        }
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
