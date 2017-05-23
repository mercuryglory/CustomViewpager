package com.wzh.customviewpager;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdvertiseActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private int[] imageResIds = { R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e };
    private String[] descriptions = { "巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };
    private TextView     mTv_decription;
    private LinearLayout mLl_point_group;
    // 用来记录上一个点的位置
    private int prePosition = 0;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    // 将广告条显示到下一个
                    int currentItem = mViewpager.getCurrentItem();
                    mViewpager.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        initView();
        initData();
        initEvent();
        int startOffset = Integer.MAX_VALUE / 2 % imageResIds.length;
        mViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - startOffset);

        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    private void initEvent() {
        // 给viewpager设置页面改变监听
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // 当页面被选中时才调用该方法
            @Override
            public void onPageSelected(int position) {
                int pos = position % imageResIds.length;

                // 先把前一个点设置为未选中
                mLl_point_group.getChildAt(prePosition).setEnabled(false);
                prePosition = pos;
                // 修改文本信息
                mTv_decription.setText(descriptions[pos]);
                mLl_point_group.getChildAt(pos).setEnabled(true);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }
        });

        mViewpager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
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
                return false;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(123123123);
        return true;
    }

    private void initData() {
        mViewpager.setAdapter(new MyAdapter());
        // 动态添加小圆点
        for (int i = 0; i < imageResIds.length; i++) {
            ImageView point = new ImageView(getApplicationContext());
            // 设置图片宽高为6个像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(6,
                    6);
            params.leftMargin = 5;
            point.setBackgroundResource(R.drawable.point_bg_selector);
            point.setLayoutParams(params);
            point.setEnabled(false);

            mLl_point_group.addView(point);
        }

    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTv_decription = (TextView) findViewById(R.id.tv_decription);
        mLl_point_group = (LinearLayout) findViewById(R.id.ll_point_group);

    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        // 如果将要展示的界面view与初始化方法返回的object对象一致的话才显示
        @Override
        public boolean isViewFromObject(View view, Object object) {
            // TODO Auto-generated method stub
            return view == object;
        }

        // 类似getView方法，对条目界面进行初始化
        /**
         * container:容器,就是viewpager自身        position:将要初始化的位置索引
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int pos = position % imageResIds.length;
            System.out.println("instantiateItem  :" + position);
            // 创建出对应界面
            ImageView iv = new ImageView(getApplicationContext());
            iv.setBackgroundResource(imageResIds[pos]);
            // 要显示的界面添加到容器
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 从容器中删除要被销毁的对象
            System.out.println("destroyItem  :" + position);
            container.removeView((View) object);
        }
    }
}
