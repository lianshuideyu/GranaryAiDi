package com.atguigu.granaryaidi.view.fragment.sharefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokeBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.share.ShareJokeAdapter;
import com.google.gson.Gson;

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


    private List<BaisiJokeBean.ListBean> lists;
    private ShareJokeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.share_joke;
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

        String url = NetLink.SHARE_JOKE;

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
    private void processData(String content) {
        BaisiJokeBean bean = new Gson().fromJson(content, BaisiJokeBean.class);
        lists = bean.getList();

//        Log.e("share", "解析==" + bean.getList().get(0).getU().getName());

        if (lists != null && lists.size() > 0) {

            adapter = new ShareJokeAdapter(context, lists);
            //设置适配器

            rv_share_joke.setAdapter(adapter);

            //设置RecyclerView的布局模式
            LinearLayoutManager manager = new LinearLayoutManager(context);

            rv_share_joke.setLayoutManager(manager);
        }
        pb_bar.setVisibility(View.GONE);

    }

    @Override
    protected void initListener() {

    }

}
