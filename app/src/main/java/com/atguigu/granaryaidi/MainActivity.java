package com.atguigu.granaryaidi;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.view.Activity.ShopWebviewActivity;
import com.atguigu.granaryaidi.view.fragment.DaRenFragment;
import com.atguigu.granaryaidi.view.fragment.MagazineFragment;
import com.atguigu.granaryaidi.view.fragment.ShareFragment;
import com.atguigu.granaryaidi.view.fragment.ShopFragment;
import com.atguigu.granaryaidi.view.fragment.UserFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.umeng.analytics.MobclickAgent;

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

    private UserFragment userFragment;

    /**
     * 设置转场动画
     */
    @Override
    public void activityAnmotion() {
        super.activityAnmotion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //方法二,从右边进来
            android.transition.Transition explode = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_right);
            getWindow().setEnterTransition(explode);
        }

    }

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

        userFragment = new UserFragment();
        fragments.add(new ShopFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new DaRenFragment());
        fragments.add(new ShareFragment());
        fragments.add(userFragment);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     当扫描成功后扫描用的Activity会自动关闭，
     在启动调用该Activity的Activity（一般是MainActivity）
     ,重写回掉方法，接收扫描到的结果
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(MainActivity.this, "内容为空", Toast.LENGTH_LONG).show();
                Log.e("saomiao","空");
            } else {
                // ScanResult 为 获取到的字符串
                String scanResult = intentResult.getContents();
//                Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();
                Log.e("saomiao",scanResult);
                Intent intent = new Intent(this, ShopWebviewActivity.class);
                intent.putExtra(NetLink.HTML_URL,scanResult);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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
