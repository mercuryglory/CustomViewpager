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

    public BannerUrlAdapter() {
    }

    public BannerUrlAdapter(Context context, String[] urls) {
        mContext = context;
        this.urls = urls;
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
        //Todo 根据图片url动态加载
        if (mOnLoadUrlListener != null) {
            mOnLoadUrlListener.onLoad(imageView, pos);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnLoadUrlListener {
        void onLoad(ImageView imageView, int position);
    }

    OnLoadUrlListener mOnLoadUrlListener;

    public void setOnLoadUrlListener(OnLoadUrlListener onLoadUrlListener) {
        mOnLoadUrlListener = onLoadUrlListener;
    }

}
