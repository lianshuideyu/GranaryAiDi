package com.atguigu.granaryaidi.view.Activity.bili;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;

import java.io.IOException;

import butterknife.InjectView;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.atguigu.granaryaidi.R.id.right_play;

public class LivePlayerActivity extends BaseActivity {


    @InjectView(R.id.video_view)
    SurfaceView videoView;
    @InjectView(R.id.bili_anim)
    ImageView biliAnim;
    @InjectView(R.id.video_start_info)
    TextView videoStartInfo;
    @InjectView(right_play)
    ImageView rightPlay;
    @InjectView(R.id.bottom_play)
    ImageView bottomPlay;
    @InjectView(R.id.bottom_love)
    ImageView bottomLove;
    @InjectView(R.id.bottom_fullscreen)
    ImageView bottomFullscreen;
    @InjectView(R.id.bottom_layout)
    RelativeLayout bottomLayout;
    @InjectView(R.id.live_layout)
    FrameLayout liveLayout;
    @InjectView(R.id.activity_live_player)
    LinearLayout activityLivePlayer;
    @InjectView(R.id.rl_bili_anim)
    RelativeLayout rl_bili_anim;
    @InjectView(R.id.user_pic)
    ImageView userPic;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.user_name)
    TextView userName;
    @InjectView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @InjectView(R.id.live_num)
    TextView liveNum;

    private IjkMediaPlayer ijkMediaPlayer;

    private SurfaceHolder holder;

    private AnimationDrawable mAnimViewBackground;

    /*
        直播播放的链接
         */
    private String playurl;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        playurl = getIntent().getStringExtra("playurl");
//        Log.e("url", "url==" + playurl);

        rl_bili_anim.setVisibility(View.VISIBLE);
        startAnim();

        initVideo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_player;
    }

    private void initVideo() {

        holder = videoView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();

        if (!TextUtils.isEmpty(playurl)) {

            playVideo(playurl);

        }
    }

    private void playVideo(String playurl) {

        try {
            ijkMediaPlayer.setDataSource(this, Uri.parse(playurl));

            ijkMediaPlayer.setDisplay(holder);

            holder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    ijkMediaPlayer.setDisplay(holder);
                }


                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            videoView.setVisibility(View.VISIBLE);
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
            stopAnim();

            rightPlay.setVisibility(View.VISIBLE);
            bottomLayout.setVisibility(View.VISIBLE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.setKeepInBackground(false);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        ijkMediaPlayer.release();
    }

    private void startAnim() {

        mAnimViewBackground = (AnimationDrawable) biliAnim.getBackground();
        mAnimViewBackground.start();
    }


    private void stopAnim() {

        mAnimViewBackground.stop();
        rl_bili_anim.setVisibility(View.GONE);
    }


    @OnClick({R.id.right_play, R.id.bottom_play, R.id.bottom_love, R.id.bottom_fullscreen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_play:

                Log.e("bili", "isplay==" + isPlay);
                ControlVideo();
                break;
            case R.id.bottom_play:

                ControlVideo();

                break;
            case R.id.bottom_love:
                showToast("送礼物");
                break;
            case R.id.bottom_fullscreen:
                break;
        }
    }

    private boolean isPlay = true;

    private void ControlVideo() {

        if (isPlay) {//暂停
            ijkMediaPlayer.pause();
            isPlay = false;
            rightPlay.setImageResource(R.drawable.ic_tv_play);
            bottomPlay.setImageResource(R.drawable.ic_portrait_play);
        } else {//开始播放
            ijkMediaPlayer.start();
            isPlay = true;
            rightPlay.setImageResource(R.drawable.ic_tv_stop);
            bottomPlay.setImageResource(R.drawable.ic_portrait_stop);
        }
    }

}
