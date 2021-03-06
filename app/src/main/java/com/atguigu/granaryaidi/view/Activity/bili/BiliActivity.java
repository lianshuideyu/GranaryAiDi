package com.atguigu.granaryaidi.view.Activity.bili;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserDongtaiFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserFenquFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserRecommendFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserZhiboFragment;
import com.atguigu.granaryaidi.view.fragment.userfragment.UserZhuifanFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class BiliActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

//    @InjectView(R.id.iv_user_icon)
//    ImageView ivUserIcon;
//    @InjectView(R.id.tv_user_name)
//    TextView tvUserName;
//    @InjectView(ll_denglu)
//    LinearLayout llDenglu;
//    @InjectView(R.id.ib_tv)
//    ImageButton ibTv;
//    @InjectView(R.id.ib_game)
//    ImageButton ibGame;
//    @InjectView(R.id.ib_download)
//    ImageButton ibDownload;
//    @InjectView(R.id.ib_serach)
//    ImageButton ibSerach;

    @InjectView(R.id.tl_user)
    TabLayout tlUser;
    @InjectView(R.id.vp_user)
    ViewPager vpUser;

    @InjectView(R.id.nav_view)
    NavigationView nav_view;
    @InjectView(R.id.drawer_layout)
    public DrawerLayout drawer_layout;

    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.appbar_layout)
    AppBarLayout appbarLayout;

    private LinearLayout ll_nav_denglu;
    private ImageView switch_night;
    private ImageView switch_message;
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
    public void initData() {

        //根据数据创建子页面
        fragments = new ArrayList<>();

        fragments.add(new UserZhiboFragment());
        fragments.add(new UserRecommendFragment());
        fragments.add(new UserZhuifanFragment());
        fragments.add(new UserFenquFragment());
        fragments.add(new UserDongtaiFragment());


        //viewpager的设置适配器
        vpUser.setAdapter(new UserFragmentAdapter(getSupportFragmentManager()));

        tlUser.setupWithViewPager(vpUser);
//        tl_user.setTabMode(MODE_FIXED);
//        tl_user.setTabGravity(GRAVITY_FILL);

        //设置初进app默认显示的为商品模块的首页 页面
        vpUser.setCurrentItem(0);
    }

    @Override
    public void initListener() {

        /**
         * 侧滑栏头部的点击事件
         */
        nav_view.setNavigationItemSelectedListener(this);
        View headerView = nav_view.getHeaderView(0);
        ll_nav_denglu = (LinearLayout) headerView.findViewById(R.id.ll_nav_denglu);
        ll_nav_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BiliActivity.this, "登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BiliActivity.this, BiliLoginActivity.class);
                startActivity(intent);
            }
        });

        switch_night = (ImageView) headerView.findViewById(R.id.switch_night);
        switch_message = (ImageView) headerView.findViewById(R.id.switch_message);
        switch_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("夜间模式");
            }
        });
        switch_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("消息");
            }
        });
    }

    @Override
    public void initView() {

        toolBar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        View view = View.inflate(this,R.layout.title_iconname,null);
        toolBar.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showToast("登录");
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });

        toolBar.setOnMenuItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bili;
    }


    /**
     * toolbar右面的三点图标
     * 加载自定义的menu布局
     * 注意布局item中需加上：app:showAsAction="always"，才可显示自定义图标
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus_title, menu);
        return true;
    }

    /**
     * 三点图标里面内容监听
     * @param item
     * @return
     * toolbar的菜单项的点击事件
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.first:
                showToast("直播");
                break;
            case R.id.one:
                showToast("游戏");
                break;
            case R.id.two:
                showToast("下载");
                break;
            case R.id.three:
                showToast("搜索");
                break;
            default:break;
        }
        return true;
    }

    /**
     * 侧滑栏里面的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //点击后关闭侧滑栏
        drawer_layout.closeDrawer(GravityCompat.START);
        switch (id) {
            case R.id.nav_home:
                showToast("首页");
                break;
            case R.id.nav_vip:
                showToast("会员");
                break;
        }

        return true;
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

    @Override
    public void onBackPressed() {
        //判断返回是否先关闭侧滑栏
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
