package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.ShopPinpaiActivity;
import com.atguigu.granaryaidi.view.adapter.shop.PinPaiAdapter;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class PinPaiFragment extends BaseFragment {

    @InjectView(R.id.lv_pinpai)
    ListView lvPinpai;

    @InjectView(R.id.pt_fresh_pinpai)
    PullToRefreshLayout pt_fresh_pinpai;
    /**
     * 联网获取品牌数据
     */
    private List<ShopPinPaiBean.DataBean.ItemsBean> items;
    private PinPaiAdapter adapter;

    private String url;
    private int num;

    @Override
    public int getLayoutId() {
        return R.layout.pinpaifragment;
    }

    @Override
    protected void initView() {
        num = 1;
        url = NetLink.SHOP_PINPAI_START + num + NetLink.SHOP_PINPAI_END;
    }

    private boolean isLoadMore = false;

    @Override
    protected void initListener() {

        /**
         * listView的点击事件
         */
        lvPinpai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //将bean对象传过去
                Intent intent = new Intent(context, ShopPinpaiActivity.class);
                intent.putExtra(NetLink.SHOP_PINPAI_LIST, items.get(position));
                startActivity(intent);

            }
        });


        pt_fresh_pinpai.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                isLoadMore = false;
                num = 1;
                getDataNet(NetLink.SHOP_PINPAI);

            }

            @Override
            public void loadMore() {

                num += 1;
                isLoadMore = true;
                if (bean != null && bean.getData().isHas_more()) {//小于数据的最大值

                    getDataNet(NetLink.SHOP_PINPAI_START + num + NetLink.SHOP_PINPAI_END);
                } else {
                    Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                    pt_fresh_pinpai.finishLoadMore();
                }
            }
        });
    }

    @Override
    protected void initData() {
        //联网请求数据
        if (!TextUtils.isEmpty(url)) {

            getDataNet(url);
        }
    }

    private void getDataNet(String url) {

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("pinpai", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("pinpai", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private ShopPinPaiBean bean;

    private void processData(String content) {
        if (isLoadMore) {//判断是上拉还是下拉刷新,isLoadMore为false是为下拉,默认为false
            //为上拉加载更多
            ShopPinPaiBean beanMore = new Gson().fromJson(content, ShopPinPaiBean.class);
            List<ShopPinPaiBean.DataBean.ItemsBean> itemsMore = beanMore.getData().getItems();

            if (itemsMore != null && itemsMore.size() > 0) {
                items.addAll(itemsMore);
                adapter.notifyDataSetChanged();
            }
        } else {

            bean = new Gson().fromJson(content, ShopPinPaiBean.class);
            items = bean.getData().getItems();

            Log.e("pinpai", "解析==" + items.get(0).getBrand_name());

            if (items != null && items.size() > 0) {

                adapter = new PinPaiAdapter(context, items);
//            //设置适配器
                lvPinpai.setAdapter(adapter);
                //添加数据

            }
        }

        pt_fresh_pinpai.finishRefresh();//刷新结束
        pt_fresh_pinpai.finishLoadMore();

    }

}
