package com.atguigu.granaryaidi.view.fragment.pinpaifragment;

import android.util.Log;
import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiListBean;
import com.atguigu.granaryaidi.view.adapter.shop.PinpaiListAdapter;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PinPaiProduct extends BaseFragment {

    @InjectView(R.id.gv_shop_pinpai)
    GridView gvShopPinpai;

    private ShopPinPaiListBean bean;

    private List<ShopPinPaiListBean.DataBean.ItemsBean> items;
    private PinpaiListAdapter adapter;

    public ShopPinPaiListBean getBean() {
        return bean;
    }

    public void setBean(ShopPinPaiListBean bean) {
        this.bean = bean;

        if (bean != null) {
            items = bean.getData().getItems();
            Log.e("pinpai","pinpailistData==" + items.size());
            if (items != null && items.size() > 0) {

                adapter = new PinpaiListAdapter(context, items);
//            //设置适配器
                gvShopPinpai.setAdapter(adapter);
                //添加数据

            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_pinpai_product;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Log.e("pinpai","pinpailistData");


    }

    @Override
    protected void initListener() {

    }

}
