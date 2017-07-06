package com.atguigu.granaryaidi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.view.fragment.DaRenFragment;
import com.atguigu.granaryaidi.view.fragment.MagazineFragment;
import com.atguigu.granaryaidi.view.fragment.ShareFragment;
import com.atguigu.granaryaidi.view.fragment.ShopFragment;
import com.atguigu.granaryaidi.view.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tab_group)
    RadioGroup tabGroup;


    private List<BaseFragment> fragments;
    //切换的位置
    private int position;

    private Fragment tempFragment;//用于缓存的Fragment

    @Override
    public void initListener() {
        tabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Log.e("TAG","checkid==" + checkid);
                //方法一
                switch (checkid) {
                    case R.id.shop_main_bg :
                        position = 0;
                        break;
                    case R.id.mgz_main_bg :
                        position = 1;
                        break;
                    case R.id.daren_main_bg :
                        position = 2;
                        break;
                    case R.id.share_main_bg :
                        position = 3;
                        break;
                    case R.id.user_main_bg :
                        position = 4;
                        break;
                }
//
                BaseFragment currentFragment = fragments.get(position);
                selectFragment(currentFragment);
            }
        });
//
//        //默认选择 会话 按键
        tabGroup.check(R.id.shop_main_bg);
    }

    /**
     * 切换Fragment
     * @param currentFragment
     */
    private void selectFragment(BaseFragment currentFragment) {
        if(currentFragment == null) {
            return;
        }

        //开启事务管理
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(currentFragment != tempFragment) {
            //第一次进来temp为空，肯定不等于

            //先隐藏之前的
            if(tempFragment != null) {
                ft.hide(tempFragment);
            }


            if(!currentFragment.isAdded()) {
                //判断是否添加过，没有就添加
                ft.add(R.id.fragment_main,currentFragment);

            }else {
                //添加过就显示缓存的
                ft.show(currentFragment);
            }

            tempFragment = currentFragment;
            ft.commit();//提交事务，不要忘记
        }
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new DaRenFragment());
        fragments.add(new ShareFragment());
        fragments.add(new UserFragment());
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}
