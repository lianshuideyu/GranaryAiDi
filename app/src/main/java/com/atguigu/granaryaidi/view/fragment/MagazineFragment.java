package com.atguigu.granaryaidi.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineProductionItemBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.DateChange;
import com.atguigu.granaryaidi.utils.DensityUtil;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.MagazineTwoActivity;
import com.atguigu.granaryaidi.view.Activity.ShopWebviewActivity;
import com.atguigu.granaryaidi.view.adapter.magzine.MagazineHomeAdapter;

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
    TextSwitcher tvDate;
    @InjectView(R.id.pb_bar)
    ProgressBar pb_bar;


    /*
    * android:textColor="#4c78a4"
            android:textSize="10sp"*/
    private MagazineHomeAdapter adapter;


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

        tvDate.setFactory(new ViewSwitcher.ViewFactory() {
            //这里 用来创建内部的视图，这里创建TextView，用来显示文字
            public View makeView() {
                TextView tv = new TextView(context);
                //设置文字大小
                tv.setTextSize(DensityUtil.dip2px(context, 5));
                //设置文字 颜色getResources().getColor(R.color.TextSwitcher)
                tv.setTextColor(Color.parseColor("#4c78a4"));

                return tv;
            }
        });

//        // 设置切入动画
        tvDate.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom));
//        // 设置切出动画
        tvDate.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_up));
//        //items是一个字符串列表，index就是动态的要显示的items中的索引
        tvDate.setText(" TODAY ");
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

                    Log.e("magzine", "解析成功==" + beans.get(0).get(0).getTopic_name());
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

    private String[] keys;
    /**
     * 集合里边套着一个集合
     */
    private ArrayList<ArrayList<MagazineProductionItemBean>> beans;

    private ArrayList<MagazineProductionItemBean> beanitems;

    private void processData(String json) {
        beans = new ArrayList<>();
        beanitems = new ArrayList<>();

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
                                    beans.add(list);
                                    beanitems.addAll(list);

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

        /**
         * 解析数据成功后 设置 数据
         */
        setData();

    }

    /**
     * 解析数据成功后 设置 数据
     */
    private void setData() {

        if (beans != null && beans.size() > 0) {
            adapter = new MagazineHomeAdapter(context, beans, beanitems, keys);

//            adapter.refresh(beans, keys);

            rlvMagazine.setAdapter(adapter);

            //设置RecyclerView的布局模式
            final GridLayoutManager manager = new GridLayoutManager(context, 1);

            rlvMagazine.setLayoutManager(manager);


            /**
             * 监听item
             * 为了 得到 item的位置-->position
             */
            rlvMagazine.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    int position = manager.findFirstVisibleItemPosition();
                    String addtime = beanitems.get(position).getAddtime();
                    String month = addtime.substring(5, 7);
                    String day = addtime.substring(8, 10);

                    String englishMon = DateChange.dateFormat(month);
                    String date = englishMon + "." + day;
                    tvDate.setText(date);

                }
            });


            /**
             * 点击事件
             */
            adapter.setOnItemClickMagazListener(new MagazineHomeAdapter.OnItemClickMagazineListener() {
                @Override
                public void onItemClick(int position) {

                    Intent intent = new Intent(context, ShopWebviewActivity.class);
                    intent.putExtra(NetLink.HTML_URL, beanitems.get(position).getTopic_url());
                    intent.putExtra(NetLink.HTML_TITLE, beanitems.get(position).getTopic_name());
                    startActivity(intent);
                }
            });

        }

        pb_bar.setVisibility(View.GONE);
    }


    @Override
    protected void initListener() {

        //标题栏的点击事件
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(context, MagazineTwoActivity.class);
//                startActivity(intent);
                //设置转场动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(context, MagazineTwoActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                } else {
                    Intent intent = new Intent(context, MagazineTwoActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
