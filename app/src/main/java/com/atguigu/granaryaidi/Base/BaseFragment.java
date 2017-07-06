package com.atguigu.granaryaidi.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/5.
 */

public abstract class BaseFragment extends Fragment {

    public Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutId() == 0) {
            TextView textView = new TextView(context);
            textView.setText("布局不能为空");
            return textView;
        }else {

            View view = View.inflate(context, getLayoutId(), null);
            ButterKnife.inject(this,view);
            return view;
        }

    }

    public abstract int getLayoutId() ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        initTitle();
        initData();

        initListener();
    }

    public void initTitle(){

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
