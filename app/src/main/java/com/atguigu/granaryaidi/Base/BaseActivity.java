package com.atguigu.granaryaidi.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //沉浸式状态栏相关
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        //转场动画
        activityAnmotion();

        setContentView(getLayoutId());
        //沉浸式状态栏相关
//        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//        View parentView = contentFrameLayout.getChildAt(0);
//        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
//            parentView.setFitsSystemWindows(true);
//        }

        ButterKnife.inject(this);

        initView();

        initData();

        initListener();
    }

    public void activityAnmotion() {

    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    /**
     * 添加布局id
     * @return
     */
    public abstract int getLayoutId() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.reset(this);
    }

    public void showToast(String message){
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(this);
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }

}
