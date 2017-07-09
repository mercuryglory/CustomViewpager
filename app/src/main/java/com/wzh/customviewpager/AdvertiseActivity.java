package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mercury.library.CarouselGroup;

import static com.wzh.customviewpager.R.drawable.e;

/**
 * created by Mercury at 2017/7/2
 * descript: 使用轮播图的示例，内部做了封装，不用在使用的Activity或Fragment中监听事件
 */
public class AdvertiseActivity extends AppCompatActivity {

    private CarouselGroup mGroup;
    private int[]    imageResIds  = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, e};
//    private String[] descriptions = {"巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
//            "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    private String[] descriptions={"巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级", "乐视网TV版大派送"};

    private String[] ss = {"https://img20.360buyimg" +
            ".com/da/jfs/t6097/97/4163948835/114126/fa9409a6/595efdb0Nd1563185.jpg",
            "https://img11.360buyimg" +
                    ".com/da/jfs/t5464/106/472798610/142359/66551a05/58ffffc8Naea1f2a2.jpg",
            "https://img12.360buyimg" +
                    ".com/da/jfs/t6100/266/4114457337/142577/51331a4f/595f3918Nad315ace.jpg",
            "https://img11.360buyimg" +
                    ".com/da/jfs/t6172/91/2178365802/97589/d12b5e8a/595ef3b1N395028d5.jpg",
            "https://img11.360buyimg" +
                    ".com/babel/jfs/t6220/341/1975835028/102643/3e135710/595aff84N87eff326.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        mGroup = (CarouselGroup) findViewById(R.id.ca_group);
        mGroup.setCarouselAdapter(imageResIds, descriptions);

        ImageView iv = (ImageView) findViewById(R.id.iv);

        RequestListener<String,GlideDrawable> listener =new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                       boolean isFirstResource) {
                Log.i("Advertise", e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model,
                                           Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.i("Advertise", "model:" + model);
                return false;
            }
        };

        Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png")
                .listener(listener)
                .into(iv);




    }




}
