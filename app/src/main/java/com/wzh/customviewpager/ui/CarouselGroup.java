package com.wzh.customviewpager.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzh.customviewpager.R;

/**
 * Created by Mercury on 2017/7/5.
 */

public class CarouselGroup extends RelativeLayout {

    CarouselViewPager mViewPager;
    private Context mContext;

    public CarouselGroup(Context context) {
        this(context, null);
    }

    public CarouselGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mViewPager = new CarouselViewPager(context);

    }


    public void setCarouselAdapter(int[] ids) {
        mViewPager.setBannerAdapter(ids);
        int size = ids.length;
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.half_transparent));

        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.CENTER_HORIZONTAL;

        textView.setTextColor(mContext.getResources().getColor(R.color.white));
        textView.setText("这是广告条的描述");
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(params1);

        linearLayout.addView(textView);
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);



        LinearLayout linearLayout1 = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.bottomMargin = 10;
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(layoutParams);

        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(8, 8);
            params2.leftMargin = 10;
            imageView.setImageResource(R.drawable.point_bg_selector);
            imageView.setLayoutParams(params2);

            linearLayout1.addView(imageView);
        }

        linearLayout.addView(linearLayout1);
        this.addView(mViewPager);
        this.addView(linearLayout, params);
    }
}
