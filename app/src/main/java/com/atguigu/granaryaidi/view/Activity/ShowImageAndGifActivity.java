package com.atguigu.granaryaidi.view.Activity;

import android.widget.ImageView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

}
