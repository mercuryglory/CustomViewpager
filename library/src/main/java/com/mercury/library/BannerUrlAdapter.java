package com.mercury.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by wang.zhonghao on 2017/7/10.
 */

public class BannerUrlAdapter extends PagerAdapter {

    private Context mContext;

    private String[] urls;

    private UrlBanner mUrlBanner;

    public BannerUrlAdapter() {
    }

    public BannerUrlAdapter(Context context, String[] urls,UrlBanner urlBanner) {
        mContext = context;
        this.urls = urls;
        mUrlBanner = urlBanner;
    }

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
        int pos = position % urls.length;
        ImageView imageView = new ImageView(mContext);
        //根据图片url动态加载，具体以何种方式加载图片暴露给开发者自己决定
        if (mUrlBanner != null) {
            mUrlBanner.loadUrlBanner(imageView, pos);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
