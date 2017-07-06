package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopSpecialBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.ShopWebviewActivity;
import com.atguigu.granaryaidi.view.adapter.SpecialAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class SpecialFragment extends BaseFragment {
    @InjectView(R.id.lv_shop_special)
    ListView lvShopSpecial;
    /**
     * 联网获取的数据
     */
    private List<ShopSpecialBean.DataBean.ItemsBean> items;
    private SpecialAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.specialfragment;
    }

    @Override
    protected void initView() {
        /**
         * item的点击事件
         */
        lvShopSpecial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(context, ShopWebviewActivity.class);
                intent.putExtra(NetLink.HTML_URL,items.get(position).getTopic_url());
                intent.putExtra(NetLink.HTML_TITLE,items.get(position).getTopic_name());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initData() {
        //联网请求数据
        getDataNet();
    }
    private void getDataNet() {
        String url = NetLink.SHOP_SPECIAL;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("special","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("special","联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopSpecialBean bean = new Gson().fromJson(content, ShopSpecialBean.class);
        items = bean.getData().getItems();

        Log.e("special","解析==" + items.get(0).getCat_name());

        if(items != null && items.size() > 0) {

            adapter = new SpecialAdapter(context,items);
            //设置适配器
            lvShopSpecial.setAdapter(adapter);
            //添加数据

        }

    }


}
