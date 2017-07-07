package com.atguigu.granaryaidi.view.fragment.darenfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenGuanzhuBean;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.DarenDetailsActivity;
import com.atguigu.granaryaidi.view.adapter.daren.DarenGuanzhuAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenGuanzhufragment extends BaseFragment {

    @InjectView(R.id.gv_daren_guanzhu)
    GridView gvDarenGuanzhu;
    /**
     * 联网获取数据
     */
    private List<DaRenGuanzhuBean.DataBean.ItemsBean.UsersBean> users;
    private DarenGuanzhuAdapter adapter;
    private String url1;

    @Override
    public int getLayoutId() {
        return R.layout.daren_guanzhu;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        //item的点击事件
        gvDarenGuanzhu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(context, DarenDetailsActivity.class);
//                intent.putExtra(NetLink.DAREN_DETAILS,items.get(position));
                intent.putExtra("uid",users.get(position).getUser_id());
                intent.putExtra("username",users.get(position).getUser_name());//达人名称
                intent.putExtra("duty",users.get(position).getUser_desc());//行业
                intent.putExtra("orig",users.get(position).getUser_image().getOrig());//头像链接


                startActivity(intent);
            }
        });
    }

    /**
     * 从上级页面传来的数据
     *
     * @param url1
     */
    public void setUrl(String url1) {
        this.url1 = url1;

//        if (!TextUtils.isEmpty(url1)) {
//
//            getDataFromNet(url1);
//        }
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

        DaRenGuanzhuBean bean = new Gson().fromJson(content, DaRenGuanzhuBean.class);
//
        users = bean.getData().getItems().getUsers();
////
        Log.e("darengz", "二级页面解析成功==" + bean.getData().getItems().getUser_name());
        if (users != null && users.size() > 0) {

            //设置适配器
            adapter = new DarenGuanzhuAdapter(context, users);

            gvDarenGuanzhu.setAdapter(adapter);
            //设置数据

        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if (!TextUtils.isEmpty(url1)) {

            getDataFromNet(url1);
        }
    }
}
