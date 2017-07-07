package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopClassifBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.ClassifyAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ClassifyFragment extends BaseFragment {
    @InjectView(R.id.gv_classify)
    GridView gvClassify;
    /**
     * 联网得到的数据
     */
    private List<ShopClassifBean.DataBean.ItemsBean> items;
    /**
     * 适配器
     */
    private ClassifyAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.classifyfragment;
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
        String url = NetLink.SHOP_CLASSIFY;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("classify","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("classify","联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopClassifBean bean = new Gson().fromJson(content, ShopClassifBean.class);
        items = bean.getData().getItems();

        Log.e("classify","解析==" + items.get(0).getCat_name());
        Log.e("classify","解析==" + bean.getData().getItems().get(0).getCover_new_img());

        if(items != null && items.size() > 0) {

            adapter = new ClassifyAdapter(context,items);
            //设置适配器
            gvClassify.setAdapter(adapter);
            //添加数据

        }

    }

    @Override
    protected void initListener() {

        /**
         * GridView的点击事件
         */
        gvClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });

    }

}
