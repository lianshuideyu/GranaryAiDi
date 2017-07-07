package com.atguigu.granaryaidi.view.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/5.
 */

public class UserFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.ib_shop_setting)
    ImageButton ibShopSetting;
    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("我的账户");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_main;
    }



    @Override
    protected void initView() {
        ibShopSetting.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.ib_shop_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_setting:
                Toast.makeText(context, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
