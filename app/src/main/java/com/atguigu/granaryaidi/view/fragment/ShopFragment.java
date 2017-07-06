package com.atguigu.granaryaidi.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.fragment.shopfragment.ClassifyFragment;
import com.atguigu.granaryaidi.view.fragment.shopfragment.GiftFragment;
import com.atguigu.granaryaidi.view.fragment.shopfragment.HomeFragment;
import com.atguigu.granaryaidi.view.fragment.shopfragment.PinPaiFragment;
import com.atguigu.granaryaidi.view.fragment.shopfragment.SpecialFragment;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * TagbLayout的标题信息
     */
    private String[] titles = new String[]{
            "分类","品牌","首页","专题","礼物"
    };
    /**
     * viewpager中的页面数据
     */
    private List<BaseFragment> fragments;
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

        //根据数据创建子页面
        fragments = new ArrayList<>();

        fragments.add(new ClassifyFragment());
        fragments.add(new PinPaiFragment());
        fragments.add(new HomeFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new GiftFragment());

        //viewpager的设置适配器
        vpShop.setAdapter(new ShopFragmentAdapter(getFragmentManager()));

        //TabPageIndicator和ViewPager关联起来
        //TabLayout和ViewPager关联起来
        tlShop.setupWithViewPager(vpShop);
        tlShop.setTabMode(TabLayout.MODE_SCROLLABLE);
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


    private class ShopFragmentAdapter extends FragmentPagerAdapter {

        public ShopFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
