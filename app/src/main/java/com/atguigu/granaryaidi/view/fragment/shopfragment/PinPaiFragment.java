package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.PinPaiAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class PinPaiFragment extends BaseFragment {

    @InjectView(R.id.lv_pinpai)
    ListView lvPinpai;
    /**
     * 联网获取品牌数据
     */
    private List<ShopPinPaiBean.DataBean.ItemsBean> items;
    private PinPaiAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.pinpaifragment;
    }

    @Override
    protected void initView() {

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
        String url = NetLink.SHOP_PINPAI;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("pinpai","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("pinpai","联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopPinPaiBean bean = new Gson().fromJson(content, ShopPinPaiBean.class);
        items = bean.getData().getItems();

        Log.e("pinpai","解析==" + items.get(0).getBrand_name());

        if(items != null && items.size() > 0) {

            adapter = new PinPaiAdapter(context,items);
//            //设置适配器
            lvPinpai.setAdapter(adapter);
            //添加数据

        }

    }

}
