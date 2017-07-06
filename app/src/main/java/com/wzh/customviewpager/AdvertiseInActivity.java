package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzh.customviewpager.ui.CarouselGroup;
import com.wzh.customviewpager.ui.CarouselViewPager;

/**
 * created by Mercury at 2017/7/2
 * descript: 使用轮播图的示例
 */
public class AdvertiseInActivity extends AppCompatActivity {

    private CarouselViewPager mViewpager;
    private CarouselGroup mGroup;
    private int[]    imageResIds  = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e};
    private String[] descriptions = {"巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    private TextView     mTv_decription;
    private LinearLayout mLl_point_group;
    // 用来记录上一个点的位置
    private int prePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise_in);
        mViewpager = (CarouselViewPager) findViewById(R.id.viewpager);
        mTv_decription = (TextView) findViewById(R.id.tv_decription);
        mLl_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        int startOffset = Integer.MAX_VALUE / 2 % imageResIds.length;
        mViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - startOffset);

        initData();
        initEvent();

        mGroup = (CarouselGroup) findViewById(R.id.ca_group);
        mGroup.setCarouselAdapter(imageResIds, descriptions);

    }

    private void initEvent() {
        // 给viewpager设置页面改变监听
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当轮播到该页面时才调用该方法
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                Log.i("native", position % imageResIds.length + "");
                int pos = position % imageResIds.length;
                // 先把前一个点设置为未选中
                mLl_point_group.getChildAt(prePosition).setEnabled(false);
                prePosition = pos;
                // 修改文本信息
                mTv_decription.setText(descriptions[pos]);
                mLl_point_group.getChildAt(pos).setEnabled(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void initData() {
        //为自定义轮播图viewpager绑定内部已定义好的适配器
        mViewpager.setBannerAdapter(imageResIds);
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

}
