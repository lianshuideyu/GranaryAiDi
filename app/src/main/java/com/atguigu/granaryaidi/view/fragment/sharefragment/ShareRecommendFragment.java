package com.atguigu.granaryaidi.view.fragment.sharefragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiRecommendBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.google.gson.Gson;

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

        /**
         * 联网
         */
        getDataNet();
    }

    /**
     * 联网
     */
    private void getDataNet() {

        String url = NetLink.SHARE_RECOMMEND;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("sharere", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);

                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("sharere", "联网失败==" + content);
                progressbar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        BaisiRecommendBean bean = new Gson().fromJson(content, BaisiRecommendBean.class);
//        lists = bean.getList();
//
        Log.e("sharere", "解析==" + bean.getList().get(0).getU().getName());
//
//        if (lists != null && lists.size() > 0) {
//
//            adapter = new ShareJokeAdapter(context, lists);
//            //设置适配器
//
//            rv_share_joke.setAdapter(adapter);
//
//            //设置RecyclerView的布局模式
//            LinearLayoutManager manager = new LinearLayoutManager(context);
//
//            rv_share_joke.setLayoutManager(manager);
//        }
        progressbar.setVisibility(View.GONE);

    }
    @Override
    protected void initListener() {

    }

}
