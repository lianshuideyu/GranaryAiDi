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
import com.atguigu.granaryaidi.view.fragment.sharefragment.ShareJokeFragment;
import com.atguigu.granaryaidi.view.fragment.sharefragment.ShareRecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.GRAVITY_FILL;
import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ShareFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.ib_shop_search)
    ImageButton ibShopSearch;
    @InjectView(R.id.ib_shop_menu)
    ImageButton ibShopMenu;
    @InjectView(R.id.tl_share)
    TabLayout tlShare;
    @InjectView(R.id.vp_share)
    ViewPager vpShare;

    /**
     * TagbLayout的标题信息
     */
    private String[] titles = new String[]{"推荐", "段子"};
    /**
     * viewpager中的页面数据
     */
    private List<BaseFragment> fragments;


    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("笑一笑十年少");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share_main;
    }

    @Override
    protected void initView() {
        ibShopMenu.setVisibility(View.VISIBLE);
        ibShopSearch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        //根据数据创建子页面
        fragments = new ArrayList<>();

        fragments.add(new ShareRecommendFragment());
        fragments.add(new ShareJokeFragment());

        //viewpager的设置适配器
        vpShare.setAdapter(new ShopFragmentAdapter(getFragmentManager()));

        //TabPageIndicator和ViewPager关联起来
        //TabLayout和ViewPager关联起来
// * MODE_FIXED   宽度始终是tl控件指定的宽度，如果标签过多，那么就无限挤压控件
// * MODE_SCROLLABLE  每个标签都保持自身宽度，一旦标签过多，给标题栏提供支持横向滑动的功能
// * GRAVITY_FILL   让每个标签平分TabLayout的全部宽度
// * GRAVITY_CENTER   让每个标签显示自身宽度，然后所有标签居中显示

        tlShare.setupWithViewPager(vpShare);
        tlShare.setTabMode(MODE_FIXED);
        tlShare.setTabGravity(GRAVITY_FILL);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.ib_shop_search, R.id.ib_shop_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_shop_menu:
                Toast.makeText(context, "筛选", Toast.LENGTH_SHORT).show();
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
