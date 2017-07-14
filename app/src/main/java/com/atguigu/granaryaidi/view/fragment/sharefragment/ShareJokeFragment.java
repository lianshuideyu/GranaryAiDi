package com.atguigu.granaryaidi.view.fragment.sharefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokeBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.share.ShareJokeAdapter;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ShareJokeFragment extends BaseFragment {

    @InjectView(R.id.rv_share_joke)
    RecyclerView rv_share_joke;
    @InjectView(R.id.pb_bar)
    ProgressBar pb_bar;
    @InjectView(R.id.pt_fresh_joke)
    PullToRefreshLayout ptFreshJoke;
    @InjectView(R.id.ib_top_sharejoke)
    ImageButton ibTopShare;


    private List<BaisiJokeBean.ListBean> lists;
    private ShareJokeAdapter adapter;


    private String url;
    private int num = 20;
    @Override
    public int getLayoutId() {
        return R.layout.share_joke;
    }

    @Override
    protected void initView() {
        url = NetLink.SHARE_BASE_JOKE + num + ".json";
    }

    @Override
    protected void initData() {

        /**
         * 联网
         */
        if (!TextUtils.isEmpty(url)) {

            getDataNet(NetLink.SHARE_JOKE);
        }
    }

    /**
     * 联网
     * @param url
     */
    private void getDataNet(String url) {


        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("share", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }
            }

            @Override
            public void onFailure(String content) {
                Log.e("share", "联网失败==" + content);
                pb_bar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 解析数据
     */
    BaisiJokeBean bean;
    private void processData(String content) {
        bean = new Gson().fromJson(content, BaisiJokeBean.class);
        lists = bean.getList();

//        Log.e("share", "解析==" + bean.getList().get(0).getU().getName());

        if (lists != null && lists.size() > 0) {

            adapter = new ShareJokeAdapter(context, lists);
            //设置适配器

            rv_share_joke.setAdapter(adapter);

            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(context);

            rv_share_joke.setLayoutManager(manager);

            if (isLoadMore) {//判断是上拉还是下拉刷新,isLoadMore为false是为下拉,默认为false
                //为上拉加载更多
                rv_share_joke.scrollToPosition(num - 20);
            }
        }
        pb_bar.setVisibility(View.GONE);

        ptFreshJoke.finishRefresh();//刷新结束
        ptFreshJoke.finishLoadMore();
    }

    private boolean isLoadMore = false;

    @Override
    protected void initListener() {
        /**
         * 回到顶部图标的点击事件
         */
        ibTopShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_share_joke.scrollToPosition(0);//回到顶部
            }
        });

        ptFreshJoke.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                isLoadMore = false;
                num = 20;
                getDataNet(NetLink.SHARE_JOKE);

            }

            @Override
            public void loadMore() {

                num += 20;
                isLoadMore = true;
                if(bean != null && num < bean.getInfo().getCount()) {//小于数据的最大值

                    getDataNet(NetLink.SHARE_BASE_JOKE + num + ".json");
                }else {
                    Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                    ptFreshJoke.finishLoadMore();
                }
            }
        });
    }

}
