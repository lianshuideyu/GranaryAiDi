package com.atguigu.granaryaidi.view.fragment.sharefragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ShareRecommendFragment extends BaseFragment {

    @InjectView(R.id.rl_share_recommend)
    RecyclerView rlShareRecommend;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public int getLayoutId() {
        return R.layout.share_recommend;
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
