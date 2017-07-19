package com.atguigu.granaryaidi.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.Activity.bili.BiliActivity;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class UserFragment extends BaseFragment {

    @InjectView(R.id.iv_startbili)
    ImageView iv_startbili;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

        iv_startbili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BiliActivity.class);
                startActivity(intent);
            }
        });
    }

}
