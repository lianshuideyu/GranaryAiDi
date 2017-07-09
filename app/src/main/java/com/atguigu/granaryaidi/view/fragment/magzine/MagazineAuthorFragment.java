package com.atguigu.granaryaidi.view.fragment.magzine;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineAuthorListBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.magzine.MagazineAuthorAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MagazineAuthorFragment extends BaseFragment {


    @InjectView(R.id.lv_author)
    ListView lvAuthor;

    /**
     * 联网获取品牌数据
     */
    private List<MagazineAuthorListBean.DataBean.ItemsBean> items;
    private MagazineAuthorAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.magazine_two_author;
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
        String url = NetLink.MAGAZINE_AUTHOR_LIST;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("magazine","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("magazine","联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        MagazineAuthorListBean bean = new Gson().fromJson(content, MagazineAuthorListBean.class);
        items = bean.getData().getItems();
//
        Log.e("magazine","解析==" + items.get(0).getNote());
//
        if(items != null && items.size() > 0) {

            adapter = new MagazineAuthorAdapter(context,items);
//            //设置适配器
            lvAuthor.setAdapter(adapter);
            //添加数据

        }

    }

    @Override
    protected void initListener() {

    }

}
