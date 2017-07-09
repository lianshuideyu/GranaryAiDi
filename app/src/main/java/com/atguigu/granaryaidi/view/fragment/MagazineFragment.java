package com.atguigu.granaryaidi.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineProductionItemBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class MagazineFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_jiantou)
    ImageView ivJiantou;
    @InjectView(R.id.rlv_magazine)
    RecyclerView rlvMagazine;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.pb_bar)
    ProgressBar pb_bar;



    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("杂志");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_magazine_main;
    }

    @Override
    protected void initView() {
        ivJiantou.setVisibility(View.VISIBLE);
        tvDate.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

        /**
         * 联网
         */
        getDataNet();
    }

    /**
     * 联网
     */
    private void getDataNet() {

        String url = NetLink.MAGAZINE_AUTHOR_LIANGCANG;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("magzine", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);

                    pb_bar.setVisibility(View.GONE);
                    Log.e("magzine", "解析成功==" + beans.get(0).getTopic_name());
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("magzine", "联网失败==" + content);
                pb_bar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData2(String content) {
//        DaRenDefaultBean bean = new Gson().fromJson(content, DaRenDefaultBean.class);
//        items = bean.getData().getItems();
//
//        Log.e("magzine", "解析==" + items.get(0).getUsername());
//
//        if (items != null && items.size() > 0) {
//
//            adapter = new DaRenDefaultAdapter(context, items);
////            //设置适配器
//            gvDarenDefault.setAdapter(adapter);
//            //添加数据
//
//        }

    }

    private String[] keys ;
    private ArrayList<MagazineProductionItemBean> beans;
    private void processData(String json) {
        beans = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            if (object != null) {
                JSONObject data = object.optJSONObject("data");
                if (data != null) {
                    JSONObject items = data.optJSONObject("items");
                    if (items != null) {
                        JSONArray arrs = items.optJSONArray("keys");
                        if (arrs != null && arrs.length() > 0) {
                            keys = new String[arrs.length()];
                            for (int i = 0; i < arrs.length(); i++) {
                                keys[i] = (String) arrs.opt(i);
                            }
                            JSONObject infos = items.optJSONObject("infos");
                            for (int i = 0; i < keys.length; i++) {
                                JSONArray jsonArray = infos.optJSONArray(keys[i]);
                                ArrayList<MagazineProductionItemBean> list = new ArrayList<>();
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(j);
                                    MagazineProductionItemBean productionBean = new MagazineProductionItemBean();

                                    productionBean.setAccess_url(jsonObject.optString("access_url"));
                                    productionBean.setTaid(jsonObject.optString("taid"));
                                    productionBean.setTopic_name(jsonObject.optString("topic_name"));
                                    productionBean.setCat_id(jsonObject.optString("cat_id"));
                                    productionBean.setAuthor_id(jsonObject.optString("author_id"));
                                    productionBean.setTopic_url(jsonObject.optString("topic_url"));
                                    productionBean.setCover_img(jsonObject.optString("cover_img"));
                                    productionBean.setCover_img_new(jsonObject.optString("cover_img_new"));
                                    productionBean.setHit_number(jsonObject.optInt("hit_number"));
                                    productionBean.setAddtime(jsonObject.optString("addtime"));
                                    productionBean.setContent(jsonObject.optString("content"));
                                    productionBean.setNav_title(jsonObject.optString("nav_title"));
                                    productionBean.setAuthor_name(jsonObject.optString("author_name"));
                                    productionBean.setCat_name(jsonObject.optString("cat_name"));

                                    list.add(productionBean);
                                }
                                if (list != null && list.size() > 0) {
                                    beans.addAll(list);
                                }
                            }
//                            if (beans != null && beans.size() > 0) {
//                                adapter.refresh(beans, keys);
//                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initListener() {

    }

}
