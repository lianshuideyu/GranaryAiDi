package com.atguigu.granaryaidi.view.Activity.bili;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.adapter.user.DanmuAdapter;
import com.atguigu.granaryaidi.view.viewmyself.CircleImageView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import master.flame.danmaku.ui.widget.DanmakuView;
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
    CircleImageView userPic;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.user_name)
    TextView userName;
    @InjectView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @InjectView(R.id.live_num)
    TextView liveNum;
    @InjectView(R.id.rl_user_message)
    RelativeLayout rl_user_message;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.lv_danmu)
    ListView lv_danmu;
    @InjectView(R.id.tanmu_content)
    EditText tanmuContent;
    @InjectView(R.id.send_tanmu)
    ImageView sendTanmu;
    @InjectView(R.id.ll_tanmucontent)
    LinearLayout ll_tanmucontent;
    @InjectView(R.id.sv_danmaku)
    DanmakuView sv_danmaku;

    private IjkMediaPlayer ijkMediaPlayer;

    private SurfaceHolder holder;

    private AnimationDrawable mAnimViewBackground;
    //是否全屏
    private boolean isFullScreen = false;
    //屏幕的高
    private int screenHeight;
    private int screenWidth;

    //视频原生的宽和高
    private int videoWidth;
    private int videoHeight;
    /*
        直播播放的链接
         */
    private String playurl;


    //设置视频的默认尺寸
    private static final int DEFUALT_SCREEN = 0;
    //全屏视频尺寸
    private static final int FULL_SCREEN = 1;
    private String userface;
    private String username;
    private int online;
    private String title;

    private List<String> danmus;
    private DanmuAdapter danmuAdapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        if (!TextUtils.isEmpty(userface) && !TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(title)) {

            tvTitle.setText(title);
            userName.setText(username);
            liveNum.setText(online + "");
            Glide.with(this)
                    .load(userface)
                    .error(R.drawable.bili_default_avatar)
                    .placeholder(R.drawable.bili_default_avatar)
                    .into(userPic);
        }
        //添加弹幕信息
        initDanmu();
    }

    private void initDanmu() {
        danmus = new ArrayList<>();
        for(int i = 0; i < 20; i++) {

            danmus.add("我是弹幕我是弹幕==" + i);

        }
        danmuAdapter = new DanmuAdapter(this,danmus);
        lv_danmu.setAdapter(danmuAdapter);
    }

    @Override
    public void initView() {

        playurl = getIntent().getStringExtra("playurl");
        userface = getIntent().getStringExtra("face");
        username = getIntent().getStringExtra("name");
        online = getIntent().getIntExtra("online", 0);
        title = getIntent().getStringExtra("title");
//        Log.e("url", "url==" + playurl);

        rl_bili_anim.setVisibility(View.VISIBLE);
        startAnim();

        initVideo();

        //得到屏幕的宽高
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;

        videoWidth = videoView.getWidth();
        videoHeight = videoView.getHeight();


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


    @OnClick({R.id.right_play, R.id.bottom_play, R.id.bottom_love
            , R.id.bottom_fullscreen ,R.id.send_tanmu})
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

                setVideoScreen();
                break;
            case R.id.send_tanmu:
                //发送弹幕
                String tanmu = tanmuContent.getText().toString().trim();
                if(!TextUtils.isEmpty(tanmu)) {
                    danmus.add(danmus.size(),tanmu);
                    danmuAdapter.notifyDataSetChanged();
                    tanmuContent.setText("");
                    lv_danmu.smoothScrollToPosition(danmus.size());
                }

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

    private void setVideoScreen() {
        /**
         * 设置为横屏
         */
        if (!isFullScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //横屏
            isFullScreen = true;

            ViewGroup.LayoutParams l = videoView.getLayoutParams();
            l.width = screenHeight;
            l.height = screenWidth;
            videoView.setLayoutParams(l);

            rl_user_message.setVisibility(View.GONE);
            lv_danmu.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            ll_tanmucontent.setVisibility(View.GONE);
            Log.e("isFullScreen", "setVideoScreen==" + "横屏");
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            //竖屏
            isFullScreen = false;
            Log.e("isFullScreen", "setVideoScreen==" + "竖屏");
            rl_user_message.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
            lv_danmu.setVisibility(View.VISIBLE);
            ll_tanmucontent.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置视频的全屏和默认
     *
     * @param videoType
     */
    private void setVideoType(int videoType) {
        switch (videoType) {
            case FULL_SCREEN:
//                isFullScreen = true;
                //按钮状态--默认
//                btnSwitchScreen.setBackgroundResource(R.drawable.btn_switch_screen_default_selector);
                //设置视频的尺寸为全屏显示
//                videoView.setVideoSize(screenWidth,screenHeight);

                ViewGroup.LayoutParams l = videoView.getLayoutParams();
                l.width = screenWidth;
                l.height = screenHeight;
                videoView.setLayoutParams(l);

                break;
            case DEFUALT_SCREEN:
                isFullScreen = false;
                //按钮状态--全屏
//                btnSwitchScreen.setBackgroundResource(R.drawable.btn_switch_screen_full_selector);
                //视频原生的宽和高
                int mVideoWidth = videoWidth;
                int mVideoHeight = videoHeight;
                //计算好要显示的宽和高
                int width = screenWidth;
                int height = screenHeight;
                //需要等比例的缩放，mVideoWidth/mVideoHeight == width/height，这才是等比例
                //先判断，哪种方式的面积小，以哪种为基准
                if (mVideoWidth * height < mVideoHeight * width) {
                    //height不变
                    width = mVideoWidth / mVideoHeight * height;
                } else if (mVideoWidth * height > mVideoHeight * width) {
                    //width不变
                    height = mVideoHeight / mVideoWidth * width;
                }

//                vv.setVideoSize(width,height);
                ViewGroup.LayoutParams l2 = videoView.getLayoutParams();
                l2.width = width;
                l2.height = height;
                videoView.setLayoutParams(l2);
                break;
        }
    }


}
