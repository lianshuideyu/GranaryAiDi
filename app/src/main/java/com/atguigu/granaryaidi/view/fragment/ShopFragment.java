package com.atguigu.granaryaidi.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.GRAVITY_FILL;
import static android.support.design.widget.TabLayout.MODE_FIXED;

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
    @InjectView(R.id.ib_shop_search)
    ImageButton ibShopSearch;
    @InjectView(R.id.ib_shop_cart)
    ImageButton ibShopCart;

    /**
     * TagbLayout的标题信息
     */
    private String[] titles = new String[]{
            "分类", "品牌", "首页", "专题", "礼物"
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

        ibShopSearch.setVisibility(View.VISIBLE);
        ibShopCart.setVisibility(View.VISIBLE);

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
// * MODE_FIXED   宽度始终是tl控件指定的宽度，如果标签过多，那么就无限挤压控件
// * MODE_SCROLLABLE  每个标签都保持自身宽度，一旦标签过多，给标题栏提供支持横向滑动的功能
// * GRAVITY_FILL   让每个标签平分TabLayout的全部宽度
// * GRAVITY_CENTER   让每个标签显示自身宽度，然后所有标签居中显示

        tlShop.setupWithViewPager(vpShop);
        tlShop.setTabMode(MODE_FIXED);
        tlShop.setTabGravity(GRAVITY_FILL);

        //设置初进app默认显示的为商品模块的首页 页面
        vpShop.setCurrentItem(2);
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


    @OnClick({R.id.ib_shop_search, R.id.ib_shop_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ib_shop_cart:
                Toast.makeText(context, "购物车", Toast.LENGTH_SHORT).show();
                break;
        }
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
