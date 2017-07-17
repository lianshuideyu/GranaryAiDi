package com.atguigu.granaryaidi.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserDongtaiFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserFenquFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserRecommendFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserZhiboFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserZhuifanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class UserFragment extends BaseFragment {


    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.ll_denglu)
    LinearLayout llDenglu;
    @InjectView(R.id.ib_tv)
    ImageButton ibTv;
    @InjectView(R.id.ib_game)
    ImageButton ibGame;
    @InjectView(R.id.ib_download)
    ImageButton ibDownload;
    @InjectView(R.id.ib_serach)
    ImageButton ibSerach;
    @InjectView(R.id.rl_basetitle)
    LinearLayout rlBasetitle;
    @InjectView(R.id.tl_user)
    TabLayout tl_user;
    @InjectView(R.id.vp_user)
    ViewPager vp_user;


    /**
     * TagbLayout的标题信息
     */
    private String[] titles = new String[]{
            "直播", "推荐", "追番", "分区", "动态"
    };
    /**
     * viewpager中的页面数据
     */
    private List<BaseFragment> fragments;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_main;
    }


    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

        //根据数据创建子页面
        fragments = new ArrayList<>();

        fragments.add(new UserZhiboFragment());
        fragments.add(new UserRecommendFragment());
        fragments.add(new UserZhuifanFragment());
        fragments.add(new UserFenquFragment());
        fragments.add(new UserDongtaiFragment());


        //viewpager的设置适配器
        vp_user.setAdapter(new UserFragmentAdapter(getFragmentManager()));

        tl_user.setupWithViewPager(vp_user);
//        tl_user.setTabMode(MODE_FIXED);
//        tl_user.setTabGravity(GRAVITY_FILL);

        //设置初进app默认显示的为商品模块的首页 页面
        vp_user.setCurrentItem(0);
    }

    @Override
    protected void initListener() {

    }


    private class UserFragmentAdapter extends FragmentPagerAdapter {

        public UserFragmentAdapter(FragmentManager fm) {
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
