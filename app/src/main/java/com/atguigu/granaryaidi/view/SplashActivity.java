package com.atguigu.granaryaidi.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.MainActivity;
import com.atguigu.granaryaidi.R;
import com.bumptech.glide.Glide;

import butterknife.InjectView;

public class SplashActivity extends BaseActivity {

    @InjectView(R.id.splash_iv)
    ImageView splashIv;
    @InjectView(R.id.activity_splash)
    RelativeLayout activitySplash;

    private CountDownTimer countDownTimer;

    private AlphaAnimation animation;
    @Override
    public void initListener() {

//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                //动画结束跳转到主页面
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        /**
         * 第一个参数是倒计时的总时长
         * 第二个参数为时间的间隔
         */
        countDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long l) {
//                Log.e("TAG","onTick" + l);
            }

            @Override
            public void onFinish() {
                //跳转到的Activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
//                Log.e("TAG","onFinish");
                /**
                 * 这里还要判断是否为第一次进入软件，并记录状态
                 */

            }
        }.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //startaAnimation();

        //加载gif图片
        Glide.with(this)
                .load(R.drawable.loading_start)
                .into(splashIv);


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
