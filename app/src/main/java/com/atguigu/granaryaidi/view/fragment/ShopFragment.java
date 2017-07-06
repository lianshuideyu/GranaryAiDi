package com.atguigu.granaryaidi.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ShopFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tl_shop)
    TabLayout tlShop;
    @InjectView(R.id.vp_shop)
    ViewPager vpShop;

    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("商店");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //viewpager的监听

        vpShop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
