package com.mercury.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by wang.zhonghao on 2017/7/10.
 */

public class BannerIdAdapter extends PagerAdapter {

    private Context mContext;

    private int[] imageResIds;

    public BannerIdAdapter() {
    }

    public BannerIdAdapter(Context context, int[] imageResIds) {
        mContext = context;
        this.imageResIds = imageResIds;
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
