package com.atguigu.granaryaidi.view.fragment.userfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.bilibili.BiliLiveBean;
import com.atguigu.granaryaidi.common.AppNetManager;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.user.BiliLiveAdapter;
import com.google.gson.Gson;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/17.
 */

public class UserZhiboFragment extends BaseFragment {

    @InjectView(R.id.rv_live)
    RecyclerView rvLive;
    @InjectView(R.id.iv_empty)
    ImageView iv_empty;
    @InjectView(R.id.tv_progress)
    TextView tv_progress;

    private BiliLiveAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.bili_live_fragment;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

        /**
         * 联网
         */
        tv_progress.setVisibility(View.VISIBLE);
        getDataNet(AppNetManager.LIVE_URL);
    }

    /**
     * 联网
     *
     * @param url
     */
    private void getDataNet(String url) {


        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("zhibo", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                    iv_empty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String content) {
                Log.e("zhibo", "联网失败==" + content);
                iv_empty.setVisibility(View.VISIBLE);
                tv_progress.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 解析数据
     */

    private void processData(String content) {
        BiliLiveBean liveBean = new Gson().fromJson(content, BiliLiveBean.class);
        if (liveBean != null && liveBean.getMessage().equals("ok")) {

            Log.e("zhibo", "解析==" + liveBean.getData().getBanner().get(0).getTitle());

            adapter = new BiliLiveAdapter(context, liveBean.getData());
//            //设置适配器
            rvLive.setAdapter(adapter);

            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(context);

            rvLive.setLayoutManager(manager);
        }

        tv_progress.setVisibility(View.GONE);
    }


    @Override
    protected void initListener() {

    }

}
