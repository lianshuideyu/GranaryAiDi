package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopClassifBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
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

//        Log.e("classify","解析==" + name);
//        Log.e("classify","解析==" + bean.getData().getItems().get(0).getCover_new_img());
    }

    @Override
    protected void initListener() {

    }

}
