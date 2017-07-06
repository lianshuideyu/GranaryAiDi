package com.atguigu.granaryaidi.view.fragment;

import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class UserFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


}
