package com.wzh.customviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzh.customviewpager.ui.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者:    wang.zhonghao
 * 创建时间:  2017/5/17
 * 描述:      ${TODO}
 */
public class FirstFragment extends Fragment {
    @Bind(R.id.btn_left)
    RadioButton       btnLeft;
    @Bind(R.id.btn_right)
    RadioButton       btnRight;
    @Bind(R.id.rg)
    RadioGroup        rg;
    @Bind(R.id.vp)
    NoScrollViewPager vp;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    FirstChildFragment  mFirstChildFragment;
    SecondChildFragment mSecondChildFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirstChildFragment = new FirstChildFragment();
        mSecondChildFragment = new SecondChildFragment();

        mFragments.add(mFirstChildFragment);
        mFragments.add(mSecondChildFragment);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        vp.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_left, R.id.btn_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                btnLeft.setChecked(true);
                btnRight.setChecked(false);
                vp.setCurrentItem(0);
                break;
            case R.id.btn_right:
                btnLeft.setChecked(false);
                btnRight.setChecked(true);
                vp.setCurrentItem(1);
                break;
        }
    }
}
