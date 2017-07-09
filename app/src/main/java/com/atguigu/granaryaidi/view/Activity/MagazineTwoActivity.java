package com.atguigu.granaryaidi.view.Activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.utils.DensityUtil;
import com.atguigu.granaryaidi.view.fragment.magzine.MagazineAuthorFragment;
import com.atguigu.granaryaidi.view.fragment.magzine.MagazineTypeFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MagazineTwoActivity extends BaseActivity {


    @InjectView(R.id.tl_magazine)
    TabLayout tlMagazine;
    @InjectView(R.id.vp_magazine)
    ViewPager vpMagazine;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;

    /**
     * TagbLayout的标题信息
     */
    private String[] titles = new String[]{"分类", "作者"};
    /**
     * viewpager中的页面数据
     */
    private List<BaseFragment> fragments;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //根据数据创建子页面
        fragments = new ArrayList<>();

        fragments.add(new MagazineTypeFragment());
        fragments.add(new MagazineAuthorFragment());

        //viewpager的设置适配器
        vpMagazine.setAdapter(new ShopFragmentAdapter(getSupportFragmentManager()));

        //TabPageIndicator和ViewPager关联起来
        //TabLayout和ViewPager关联起来
// * MODE_FIXED   宽度始终是tl控件指定的宽度，如果标签过多，那么就无限挤压控件
// * MODE_SCROLLABLE  每个标签都保持自身宽度，一旦标签过多，给标题栏提供支持横向滑动的功能
// * GRAVITY_FILL   让每个标签平分TabLayout的全部宽度
// * GRAVITY_CENTER   让每个标签显示自身宽度，然后所有标签居中显示

        tlMagazine.setupWithViewPager(vpMagazine);

        /**
         * 为了 设置 tablayout下边指示器的宽度
         */
        setIndicator(tlMagazine, DensityUtil.px2dip(this,170),DensityUtil.px2dip(this,170));
        //设置初进app默认显示的为作者 页面
        vpMagazine.setCurrentItem(1);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_magazine_two;
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {

        finish();
    }

    private class ShopFragmentAdapter extends FragmentPagerAdapter {


        public ShopFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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


    /**
     * 为了 设置 tablayout下边指示器的宽度
     */
    public void setIndicator (TabLayout tabs,int leftDip,int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
