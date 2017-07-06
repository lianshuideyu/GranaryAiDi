package com.atguigu.granaryaidi.view;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atguigu.granaryaidi.MainActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.Base.BaseActivity;

import butterknife.InjectView;

public class SplashActivity extends BaseActivity {

    @InjectView(R.id.splash_iv)
    ImageView splashIv;
    @InjectView(R.id.activity_splash)
    RelativeLayout activitySplash;

    private AlphaAnimation animation;
    @Override
    public void initListener() {

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束跳转到主页面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        startaAnimation();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    private void startaAnimation() {
        animation = new AlphaAnimation(0f, 1.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);

        splashIv.startAnimation(animation);
    }
}
