package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.content.Intent;
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
import com.atguigu.granaryaidi.view.Activity.ShopTypeActivity;
import com.atguigu.granaryaidi.view.adapter.ClassifyAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

import static com.atguigu.granaryaidi.common.NetLink.ACC_URL;
import static com.atguigu.granaryaidi.common.NetLink.ART_URL;
import static com.atguigu.granaryaidi.common.NetLink.BABYWEAR_URL;
import static com.atguigu.granaryaidi.common.NetLink.BEAUTY_URL;
import static com.atguigu.granaryaidi.common.NetLink.BOOK_URL;
import static com.atguigu.granaryaidi.common.NetLink.CATE_URL;
import static com.atguigu.granaryaidi.common.NetLink.FITMENT_URL;
import static com.atguigu.granaryaidi.common.NetLink.FROCK_URL;
import static com.atguigu.granaryaidi.common.NetLink.GIFT_URL;
import static com.atguigu.granaryaidi.common.NetLink.HOUSE_URL;
import static com.atguigu.granaryaidi.common.NetLink.KITCHEN_URL;
import static com.atguigu.granaryaidi.common.NetLink.MENWEAR_URL;
import static com.atguigu.granaryaidi.common.NetLink.NUMERICAL_URL;
import static com.atguigu.granaryaidi.common.NetLink.OUTDOORS_URL;
import static com.atguigu.granaryaidi.common.NetLink.PLANT_URL;
import static com.atguigu.granaryaidi.common.NetLink.PLAY_URL;
import static com.atguigu.granaryaidi.common.NetLink.RECOMMEND_URL;
import static com.atguigu.granaryaidi.common.NetLink.SHOE_URL;
import static com.atguigu.granaryaidi.common.NetLink.STATIONERY_URL;

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

    private String[] typeUrls = new String[]{
            HOUSE_URL,FITMENT_URL,STATIONERY_URL,NUMERICAL_URL,PLAY_URL,
            KITCHEN_URL,CATE_URL,MENWEAR_URL,FROCK_URL,BABYWEAR_URL,
            SHOE_URL,ACC_URL,BEAUTY_URL,OUTDOORS_URL,PLANT_URL,BOOK_URL,
            GIFT_URL,RECOMMEND_URL,ART_URL

    };
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

                Intent intent = new Intent(context, ShopTypeActivity.class);
                //将对应的类型链接传到 商品列表页面
                intent.putExtra(NetLink.SHOP_URL,typeUrls[position]);
                startActivity(intent);
            }
        });

    }

}
