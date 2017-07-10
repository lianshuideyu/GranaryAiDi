package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.Shoptwo.ShopTwoHomeBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.shop.HomeAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.rv_shop_home)
    RecyclerView rvShopHome;
    @InjectView(R.id.pb_shop_home)
    ProgressBar pbShopHome;
    @InjectView(R.id.tv_shop_home)
    TextView tvShopHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;


    private List<ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX> lists;
    private HomeAdapter adapter;
    /**
     * RecyclerView的布局管理器
     */
    private GridLayoutManager manager;

    /**
     * 联网获取数据
     */

    @Override
    public int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {


        //联网请求数据
        getDataNet();
    }

    private void getDataNet() {
        String url = NetLink.SHOP_HOME;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("home", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }
            }
            @Override
            public void onFailure(String content) {
                Log.e("home", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopTwoHomeBean bean = new Gson().fromJson(content, ShopTwoHomeBean.class);

        lists = bean.getData().getItems().getList();

//        Log.e("home", "解析==" + bean.getData().getItems().getList().get(0).getOne().getTopic_name());

        if (lists != null && lists.size() > 0) {

            adapter = new HomeAdapter(context, lists);
//            //设置适配器
            rvShopHome.setAdapter(adapter);
            //添加数据

            //设置RecyclerView的布局模式
            manager = new GridLayoutManager(context,1);
            /**
             * 通过GridLayoutManager监听recyclerview的位置变化
             */
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    Log.e("home","setSpanSizeLookup==" + position);
                    if(position <= 2) {
                        ibTop.setVisibility(View.GONE);
                    }else {
                        ibTop.setVisibility(View.VISIBLE);
                    }

                    //只能返回1
                    return 1;
                }
            });
            rvShopHome.setLayoutManager(manager);
        } else {
            //么有数据
            tvShopHome.setVisibility(View.VISIBLE);
        }
        //进入processbar消失
        pbShopHome.setVisibility(View.GONE);

    }

    @Override
    protected void initListener() {

        /**
         * 回到顶部图标的点击事件
         */
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvShopHome.scrollToPosition(0);//回到顶部
            }
        });
    }


}
