package com.atguigu.granaryaidi.view.fragment.darenfragment;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenLikeBean;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.DarenDetailsActivity;
import com.atguigu.granaryaidi.view.adapter.daren.DarenLikeAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenLikefragment extends BaseFragment {

    @InjectView(R.id.gv_daren_recommend)
    GridView gvDarenRecommend;

    private String url;
    private List<DaRenLikeBean.DataBean.ItemsBean.GoodsBean> goods;
    private DarenLikeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.daren_recommend;
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
     * @param url
     */
    public void setUrl(String url) {

        this.url = url;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (!TextUtils.isEmpty(url)) {

            getDataFromNet(url);
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
                Log.e("darenlike", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("darenlike", "联网失败==" + content);
            }
        });


    }

    /**
     * 解析数据
     */
    private boolean isFirst = false;

    private void processData(String content) {

        DaRenLikeBean bean = new Gson().fromJson(content, DaRenLikeBean.class);

        Log.e("darenlike", "二级页面解析成功==" + bean.getData().getItems().getUser_name());
        goods = bean.getData().getItems().getGoods();
        if (goods != null && goods.size() > 0) {

            //设置适配器
            adapter = new DarenLikeAdapter(context, goods);

            gvDarenRecommend.setAdapter(adapter);
            //设置数据

            //设置数据
            Activity instance = DarenDetailsActivity.getInstance();
            DarenDetailsActivity activity = (DarenDetailsActivity) instance;

            if (!isFirst) {
//                activity.rbDarenLike.setText("喜欢\n" + bean.getData().getNum_items());
                isFirst = true;
            }
        }

    }
}
