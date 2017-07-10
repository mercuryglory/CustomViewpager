package com.mercury.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Mercury on 2017/7/5.
 * 轮播图控件，包含轮播图，描述以及引导点
 */

public class CarouselGroup extends RelativeLayout {

    CarouselViewPager mViewPager;
    private Context mContext;
    private float   pointMarginLeft;
    private float   textPaddingLeft;
    private float   textPaddingTop;
    private float   textPaddingRight;
    private float   textPaddingBottom;
    private int     textColor;
    private float   pointGroupBottom;
    private int     resourceId;
    private int     backGroundColor;
    private int delayTime;

    public CarouselGroup(Context context) {
        this(context, null);
    }

    public CarouselGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CarouselGroup,
                defStyleAttr, 0);
        pointMarginLeft = a.getDimension(R.styleable.CarouselGroup_pointMarginLeft, 10);
        textPaddingLeft = a.getDimension(R.styleable.CarouselGroup_textPaddingLeft, 10);
        textPaddingTop = a.getDimension(R.styleable.CarouselGroup_textPaddingTop, 10);
        textPaddingRight = a.getDimension(R.styleable.CarouselGroup_textPaddingRight, 10);
        textPaddingBottom = a.getDimension(R.styleable.CarouselGroup_textPaddingBottom, 10);
        resourceId = a.getResourceId(R.styleable.CarouselGroup_pointRes, R.drawable
                .point_bg_selector);
        textColor = a.getColor(R.styleable.CarouselGroup_textColor, Color.rgb(255, 255, 255));
        pointGroupBottom = a.getDimension(R.styleable.CarouselGroup_pointGroupBottom, 15);
        backGroundColor = a.getColor(R.styleable.CarouselGroup_backgroundColor, Color.argb(127,
                0, 0, 0));
        delayTime = a.getInt(R.styleable.CarouselGroup_delayTime, 2000);

        a.recycle();

        mViewPager = new CarouselViewPager(context, delayTime);

    }


    public void setCarouselAdapter(int[] imageResIds, String[] description) {

        int size = imageResIds.length;
        mViewPager.setBannerAdapter(imageResIds);

        //创建轮播图底部的线性布局，纵向排列
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(backGroundColor);

        //创建显示在该布局中上部的描述文字
        TextView textView = new TextView(mContext);
        textView.setTextColor(textColor);
        if (description != null && description.length == size) {
            textView.setText(description[0]);
        } else {
            textView.setVisibility(GONE);
        }

        textView.setPadding((int) textPaddingLeft, (int) textPaddingTop, (int) textPaddingRight,
                (int) textPaddingBottom);
        //该textview在线性布局中的摆放方式
        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER_HORIZONTAL;
        textView.setLayoutParams(textParam);
        linearLayout.addView(textView);

        //创建一个横向的线性布局，动态的添加引导点
        LinearLayout llpointGroup = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.bottomMargin = (int) pointGroupBottom;
        llpointGroup.setOrientation(LinearLayout.HORIZONTAL);
        llpointGroup.setLayoutParams(layoutParams);

        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams pointParam = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pointParam.leftMargin = (int) pointMarginLeft;
            imageView.setImageResource(resourceId);
            imageView.setLayoutParams(pointParam);
            imageView.setSelected(false);

            llpointGroup.addView(imageView);
        }

        linearLayout.addView(llpointGroup);
        this.addView(mViewPager);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        this.addView(linearLayout, params);

        mViewPager.addBannerIdListener(description, textView, llpointGroup);

    }

    public void setCarouselAdapter(String[] urls, String[] description, UrlBanner urlBanner) {

        int size = urls.length;
        mViewPager.setUrlAdapter(urls, urlBanner);

        //创建轮播图底部的线性布局，纵向排列
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(backGroundColor);

        //创建显示在该布局中上部的描述文字
        TextView textView = new TextView(mContext);
        textView.setTextColor(textColor);
        if (description != null && description.length == size) {
            textView.setText(description[0]);
        } else {
            textView.setVisibility(GONE);
        }

        textView.setPadding((int) textPaddingLeft, (int) textPaddingTop, (int) textPaddingRight,
                (int) textPaddingBottom);
        //该textview在线性布局中的摆放方式
        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER_HORIZONTAL;
        textView.setLayoutParams(textParam);
        linearLayout.addView(textView);

        //创建一个横向的线性布局，动态的添加引导点
        LinearLayout llpointGroup = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.bottomMargin = (int) pointGroupBottom;
        llpointGroup.setOrientation(LinearLayout.HORIZONTAL);
        llpointGroup.setLayoutParams(layoutParams);

        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams pointParam = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pointParam.leftMargin = (int) pointMarginLeft;
            imageView.setImageResource(resourceId);
            imageView.setLayoutParams(pointParam);
            imageView.setSelected(false);

            llpointGroup.addView(imageView);
        }

        linearLayout.addView(llpointGroup);
        this.addView(mViewPager);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        this.addView(linearLayout, params);

        mViewPager.addBannerUrlListener(description, textView, llpointGroup);

    }
}
