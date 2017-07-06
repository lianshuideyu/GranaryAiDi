package com.atguigu.granaryaidi.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenDefaultBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.DaRenDefaultAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.gv_daren_default)
    GridView gvDarenDefault;

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

    }

    @Override
    protected void initData() {
        //联网请求数据
        getDataNet();
    }

    @Override
    protected void initListener() {

    }

    private void getDataNet() {

        String url = NetLink.DAREN_DEFAULT;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("DaRen","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("DaRen","联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        DaRenDefaultBean bean = new Gson().fromJson(content, DaRenDefaultBean.class);
        items = bean.getData().getItems();

        Log.e("DaRen","解析==" + items.get(0).getUsername());

        if(items != null && items.size() > 0) {

            adapter = new DaRenDefaultAdapter(context,items);
//            //设置适配器
            gvDarenDefault.setAdapter(adapter);
            //添加数据

        }

    }

}
