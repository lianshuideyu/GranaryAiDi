package com.atguigu.granaryaidi.view.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokeDetailBean;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokePinglunBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.share.ShareJokeDetailAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShareJokeDetailActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.rv_share_joke_detail)
    RecyclerView rvShareJokeDetail;
    private BaisiJokeDetailBean jokebean;

    private String url;
    private ShareJokeDetailAdapter adapter;
    private View header;

    @Override
    public void initListener() {

    }


    @Override
    public void initView() {

        jokebean = (BaisiJokeDetailBean) getIntent().getSerializableExtra("jokebean");

        ibShopBack.setVisibility(View.VISIBLE);
        tvTitle.setText("评论");
        if (jokebean != null) {

            url = NetLink.SHARE_BASE_JOKE_DETAILA + jokebean.getId() + NetLink.SHARE_BASE_JOKE_DETAILB;
        }

//        header = RecyclerViewHeader.fromXml(this, R.layout.joke_header);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_share_joke_detail;
    }


    @OnClick(R.id.ib_shop_back)
    public void onViewClicked() {

        finish();
    }

    @Override
    public void initData() {

        /**
         * 联网
         */
        if (!TextUtils.isEmpty(url)) {

            getDataNet(url);
        }
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
                Log.e("sharejoke", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }
            }

            @Override
            public void onFailure(String content) {
                Log.e("sharejoke", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        BaisiJokePinglunBean bean = new Gson().fromJson(content, BaisiJokePinglunBean.class);

        List<BaisiJokePinglunBean.NormalBean.ListBean> hotlist = bean.getHot().getList();
        List<BaisiJokePinglunBean.NormalBean.ListBean> normallist = bean.getNormal().getList();
//        Log.e("sharejoke", "解析==" + bean.getNormal().getList().get(0).getContent());

        List<BaisiJokePinglunBean.NormalBean.ListBean> allList = new ArrayList<>();
        if (normallist != null && normallist.size() > 0) {

            adapter = new ShareJokeDetailAdapter(ShareJokeDetailActivity.this, normallist,jokebean);

            //设置适配器
            if (hotlist != null && hotlist.size() > 0) {
                adapter.setHotlist(hotlist);
                allList.addAll(hotlist);
                allList.addAll(normallist);
                adapter.setAllList(allList);
            }else {
                allList.addAll(normallist);
                adapter.setAllList(allList);
            }

            rvShareJokeDetail.setAdapter(adapter);

            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(ShareJokeDetailActivity.this);

            rvShareJokeDetail.setLayoutManager(manager);
        }

    }

}
