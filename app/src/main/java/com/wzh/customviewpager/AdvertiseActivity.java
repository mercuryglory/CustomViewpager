package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mercury.library.CarouselGroup;

/**
 * created by Mercury at 2017/7/2
 * descript: 使用轮播图的示例，内部做了封装，不用在使用的Activity或Fragment中监听事件
 */
public class AdvertiseActivity extends AppCompatActivity {

    private CarouselGroup mGroup;
    private int[]    imageResIds  = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e};
//    private String[] descriptions = {"巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
//            "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    private String[] descriptions={"巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级", "乐视网TV版大派送"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        mGroup = (CarouselGroup) findViewById(R.id.ca_group);
        mGroup.setCarouselAdapter(imageResIds, descriptions);

    }


}
