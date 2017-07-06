package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wzh.customviewpager.CustomViewPager.onPageChangeListener;

public class MainActivity extends AppCompatActivity {

	private CustomViewPager mCvp;
	private int[] imageResIds = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6 };
	private RadioGroup mRadiogroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		initData();
	}

	private void initEvent() {
		mCvp.setOnPageChangeListener(new onPageChangeListener() {
			
			@Override
			public void onPageChange(int position) {
				// position刚好与RadioButton的id一一对应；position看作是单选按钮的id
				mRadiogroup.check(position);
			}
		});
		
		mRadiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mCvp.setCurrentItem(checkedId);
				
			}
		});
	}

	private void initData() {
		// 将图片添加到ViewGroup中展示
		for (int i = 0; i < imageResIds.length; i++) {
			ImageView iv = new ImageView(getApplicationContext());
			iv.setBackgroundResource(imageResIds[i]);
			mCvp.addView(iv);
		}

		//添加视图,测量所有子控件
		View view=View.inflate(getApplicationContext(), R.layout.viewpager_item, null);
		mCvp.addView(view, 1);
		
		// 动态添加单选按钮
		for (int i = 0; i < mCvp.getChildCount(); i++) {
			RadioButton ra = new RadioButton(getApplicationContext());
			ra.setId(i);
			if (i == 0) {
				ra.setChecked(true);
			}
			mRadiogroup.addView(ra);
		}
	}	

	private void initView() {
		mCvp = (CustomViewPager) findViewById(R.id.cvp);
		mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);
	}

}
