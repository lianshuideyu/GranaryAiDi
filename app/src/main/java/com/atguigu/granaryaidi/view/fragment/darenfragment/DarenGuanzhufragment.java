package com.atguigu.granaryaidi.view.fragment.darenfragment;

import android.text.TextUtils;
import android.util.Log;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.utils.HttpUtils;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenGuanzhufragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    /**
     * 从上级页面传来的数据
     *
     * @param url1
     */
    public void setUrl(String url1) {
        if (!TextUtils.isEmpty(url1)) {

            getDataFromNet(url1);
        }
    }

    /**
     * 联网
     *
     * @param url
     */
    private void getDataFromNet(String url) {

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("darengz", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("darengz", "联网失败==" + content);
            }
        });


    }

    /**
     * 解析数据
     */
    private void processData(String content) {

//        DaRenRecommendBean bean = new Gson().fromJson(content, DaRenRecommendBean.class);
//
//        bean.getData().getItems();
////
//        Log.e("darengz", "二级页面解析成功==" + bean.getData().getItems().getUser_name());
//        goods = bean.getData().getItems().getGoods();
//        if (goods != null && goods.size() > 0) {
//
//            //设置适配器
//            adapter = new DarenRecommendAdapter(context, goods);
//
//            gvDarenRecommend.setAdapter(adapter);
//            //设置数据
//
//        }

    }
}
