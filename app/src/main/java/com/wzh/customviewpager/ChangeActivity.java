package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.wzh.customviewpager.ui.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ChangeActivity extends AppCompatActivity {
    List<Fragment> fragments = new ArrayList<>();
    MyAdapter         mMyAdapter;
    NoScrollViewPager vp;
    RadioButton       btnLeft;
    RadioButton       btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        fragments.add(new FirstChildFragment());
        fragments.add(new SecondChildFragment());
        vp = (NoScrollViewPager) findViewById(R.id.vp);
        btnLeft = (RadioButton) findViewById(R.id.btn_left);
        btnRight = (RadioButton) findViewById(R.id.btn_right);
        mMyAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(mMyAdapter);

    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public void setLeft(View view) {
        btnLeft.setChecked(true);
        btnRight.setChecked(false);
        vp.setCurrentItem(0);
    }

    public void setRight(View view) {
        btnLeft.setChecked(false);
        btnRight.setChecked(true);
        vp.setCurrentItem(1);
    }
}

