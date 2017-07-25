package com.atguigu.granaryaidi.view.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineProductionItemBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.magzine.MagazineHomeAdapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.InjectView;

public class MagazineAuthorActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_jiantou)
    ImageView ivJiantou;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.rlv_magazine_author)
    RecyclerView rlvMagazineAuthor;
    @InjectView(R.id.pb_bar_author)
    ProgressBar pbBarAuthor;

    private String author_id;
    private MagazineHomeAdapter adapter;
    private String author_name;

    @Override
    public void initListener() {

        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //设置转场动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(MagazineAuthorActivity.this, MagazineTwoActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(MagazineAuthorActivity.this).toBundle());
                } else {
                    Intent intent = new Intent(MagazineAuthorActivity.this, MagazineTwoActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    /**
     * 设置转场动画
     */
    @Override
    public void activityAnmotion() {
        super.activityAnmotion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //方法二,从右边进来
            android.transition.Transition explode = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_right);
            getWindow().setEnterTransition(explode);
        }

    }

    @Override
    public void initData() {

        /**
         * 联网
         */
        if(!TextUtils.isEmpty(author_id)) {

            getDataNet();
        }
    }

    /**
     * 联网
     */
    private void getDataNet() {

        String url = NetLink.MAGAZINE_AUTHOR_START + author_id + NetLink.MAGAZINE_AUTHOR_END;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("magzineauthor", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);

                    Log.e("magzineauthor", "解析成功==" + beans.get(0).get(0).getTopic_name());
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("magzine", "联网失败==" + content);
                pbBarAuthor.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initView() {
        //上层页面将作者的id传过来
        author_id = getIntent().getStringExtra(NetLink.MAGAZINE_AUTHOR_ID);
        author_name = getIntent().getStringExtra(NetLink.MAGAZINE_AUTHOR_NAME);
        tvTitle.setText("杂志·" + author_name);

        ivJiantou.setVisibility(View.VISIBLE);
        ibShopBack.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_magazine_author;
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


            adapter = new MagazineHomeAdapter(MagazineAuthorActivity.this, beans, beanitems, keys);

//            adapter.refresh(beans, keys);

            rlvMagazineAuthor.setAdapter(adapter);

            //设置RecyclerView的布局模式
            GridLayoutManager manager = new GridLayoutManager(MagazineAuthorActivity.this, 1);

            rlvMagazineAuthor.setLayoutManager(manager);

            /**
             * 点击事件
             */
            adapter.setOnItemClickMagazListener(new MagazineHomeAdapter.OnItemClickMagazineListener() {
                @Override
                public void onItemClick(int position) {

                    Intent intent = new Intent(MagazineAuthorActivity.this, ShopWebviewActivity.class);
                    intent.putExtra(NetLink.HTML_URL, beanitems.get(position).getTopic_url());
                    intent.putExtra(NetLink.HTML_TITLE, beanitems.get(position).getTopic_name());
                    startActivity(intent);
                }
            });

        }

        pbBarAuthor.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
