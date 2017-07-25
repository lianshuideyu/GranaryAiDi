package com.atguigu.granaryaidi.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.MainActivity;
import com.atguigu.granaryaidi.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.umeng.analytics.MobclickAgent;

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


//        /**
//         * 第一个参数是倒计时的总时长
//         * 第二个参数为时间的间隔
//         */
//        countDownTimer = new CountDownTimer(5000, 500) {
//            @Override
//            public void onTick(long l) {
////                Log.e("TAG","onTick" + l);
//            }
//
//            @Override
//            public void onFinish() {
//                //跳转到的Activity
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
////                Log.e("TAG","onFinish");
//                /**
//                 * 这里还要判断是否为第一次进入软件，并记录状态
//                 */
//
//            }
//        }.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        //加载gif图片
//        Glide.with(this)
//                .load(R.drawable.loading_start)
//                .into(splashIv);

        loadSplashGif();
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;

            if (what == 1) {

                /**
                 * 这里还要判断是否为第一次进入软件，并记录状态
                 */
                //设置转场动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
                    finish();
                } else {
                    //跳转到的Activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }
    };

    private void loadSplashGif() {

        Glide.with(this)
                .load(R.drawable.loading_start)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final GlideDrawable resource, Integer model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {

                        int durcation = 0;

                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            durcation += decoder.getDelay(i);
                            //durcation为获取到的动画播放的事件
                        }
                        handler.sendEmptyMessageDelayed(1, durcation);

                        return false;
                    }
                }).into(new GlideDrawableImageViewTarget(splashIv, 1)); // 设置播放一次

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
