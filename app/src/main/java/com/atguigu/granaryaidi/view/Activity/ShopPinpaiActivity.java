package com.atguigu.granaryaidi.view.Activity;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;

public class ShopPinpaiActivity extends BaseActivity {


    @InjectView(R.id.iv_pinpai_icon)
    ImageView ivPinpaiIcon;
    @InjectView(R.id.tv_pinpai_name)
    TextView tvPinpaiName;
    @InjectView(R.id.rg_pinpai)
    RadioGroup rgPinpai;
    @InjectView(R.id.fl_pinpai)
    FrameLayout flPinpai;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_pinpai;
    }

}
