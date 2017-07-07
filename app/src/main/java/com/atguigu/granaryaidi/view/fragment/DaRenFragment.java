package com.atguigu.granaryaidi.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenDefaultBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.DarenDetailsActivity;
import com.atguigu.granaryaidi.view.adapter.DaRenDefaultAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.gv_daren_default)
    GridView gvDarenDefault;
    @InjectView(R.id.ib_shop_search)
    ImageButton ibShopSearch;
    @InjectView(R.id.ib_shop_menu)
    ImageButton ibShopMenu;

    /**
     * 联网获取数据
     */
    private List<DaRenDefaultBean.DataBean.ItemsBean> items;
    private DaRenDefaultAdapter adapter;

    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("达人");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daren_main;
    }

    @Override
    protected void initView() {
        ibShopMenu.setVisibility(View.VISIBLE);
        ibShopSearch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        //联网请求数据
        getDataNet();
    }

    @Override
    protected void initListener() {

        //GridView的点击事件
        gvDarenDefault.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //将bean对象传过去
                Intent intent = new Intent(context, DarenDetailsActivity.class);
                intent.putExtra(NetLink.DAREN_DETAILS,items.get(position));
                startActivity(intent);
            }
        });

    }

    private void getDataNet() {

        String url = NetLink.DAREN_DEFAULT;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("DaRen", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("DaRen", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        DaRenDefaultBean bean = new Gson().fromJson(content, DaRenDefaultBean.class);
        items = bean.getData().getItems();

        Log.e("DaRen", "解析==" + items.get(0).getUsername());

        if (items != null && items.size() > 0) {

            adapter = new DaRenDefaultAdapter(context, items);
//            //设置适配器
            gvDarenDefault.setAdapter(adapter);
            //添加数据

        }

    }


    @OnClick({R.id.ib_shop_search, R.id.ib_shop_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_shop_menu:
                Toast.makeText(context, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
