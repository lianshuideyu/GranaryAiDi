package com.atguigu.granaryaidi.view.Activity.bili;

import android.net.Uri;
import android.text.TextUtils;
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
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LivePlayerActivity extends BaseActivity {


    @InjectView(R.id.video_view)
    SurfaceView videoView;
    @InjectView(R.id.bili_anim)
    ImageView biliAnim;
    @InjectView(R.id.video_start_info)
    TextView videoStartInfo;
    @InjectView(R.id.right_play)
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

    private IjkMediaPlayer ijkMediaPlayer;

    private SurfaceHolder holder;

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

        initVideo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_player;
    }

    private void initVideo() {

        holder = videoView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();

        if(!TextUtils.isEmpty(playurl)) {

            playVideo(playurl);
            videoView.setVisibility(View.VISIBLE);
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
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
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
}
