package com.atguigu.granaryaidi.view.Activity;

import android.widget.ImageView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.umeng.analytics.MobclickAgent;

import butterknife.InjectView;

public class ShowImageAndGifActivity extends BaseActivity {

    @InjectView(R.id.iv_image_gif)
    ImageView ivPhoto;
    private String url;
//    private ImageOptions imageOptions;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Glide.with(this)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivPhoto);
    }

    @Override
    public void initView() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_image_and_gif;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
