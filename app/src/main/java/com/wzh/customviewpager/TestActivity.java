package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.fl_content)
    FrameLayout  flContent;
    @Bind(R.id.rb_first)
    RadioButton  rbFirst;
    @Bind(R.id.rb_second)
    RadioButton  rbSecond;
    @Bind(R.id.rb_third)
    RadioButton  rbThird;
    @Bind(R.id.activity_test)
    LinearLayout activityTest;

    Fragment[] mFragments;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();

        mFragments = new Fragment[]{firstFragment, secondFragment, thirdFragment};

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content, firstFragment).commit();
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.hide(mFragments[mIndex]);
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.remove(mFragments[index]);
        ft.commit();

        mIndex = index;
    }

    @OnClick({R.id.rb_first, R.id.rb_second, R.id.rb_third})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_first:
                setIndexSelected(0);
                break;
            case R.id.rb_second:
                setIndexSelected(1);
                break;
            case R.id.rb_third:
                setIndexSelected(2);
                break;
        }
    }
}
