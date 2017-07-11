package com.atguigu.granaryaidi.view.Activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopTypeListBean;
import com.atguigu.granaryaidi.cart.ShopCartActivity;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.shop.ClassifyListAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShopTypeActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.ib_shop_cart)
    ImageButton ibShopCart;
    @InjectView(R.id.ll_price_check)
    LinearLayout llPriceCheck;
    @InjectView(R.id.gv_shop_type)
    GridView gvShopType;

    /**
     * 联网链接
     */
    private String url;
    /**
     * 联网获取的数据集合
     */
    private List<ShopTypeListBean.DataBean.ItemsBean> items;
    private ClassifyListAdapter adapter;

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
    public void initListener() {

        gvShopType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(ShopTypeActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("goods_id",items.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

        //联网
        getDataFromNet();
    }



    @Override
    public void initView() {
        //获取传递过来的联网链接
        url = getIntent().getStringExtra(NetLink.SHOP_URL);
        tvTitle.setText("商店");

        //标题栏的按键显示
        ibShopBack.setVisibility(View.VISIBLE);
        ibShopCart.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_type;
    }


    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.ib_shop_back, R.id.ll_price_check,R.id.ib_shop_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                //点击返回
                finish();

                break;
            case R.id.ll_price_check:
                //点击价格筛选--此处应该是popuwindow
                showToast("价格筛选");

                break;
            case R.id.ib_shop_cart:
//                showToast("购物车");
                Intent intent3 = new Intent(ShopTypeActivity.this, ShopCartActivity.class);
                startActivity(intent3);
                break;
        }
    }

    /**
     * 联网
     */
    private void getDataFromNet() {
        if(TextUtils.isEmpty(url)) {
            new NullPointerException("联网链接为空");
            return;
        }

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("shoptype","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("shoptype","联网失败==" + content);
            }
        });
    }
    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopTypeListBean bean = new Gson().fromJson(content, ShopTypeListBean.class);
        items = bean.getData().getItems();
//
        Log.e("shoptype","解析==" + items.get(0).getGoods_name());

        if(items != null && items.size() > 0) {
//
            adapter = new ClassifyListAdapter(ShopTypeActivity.this,items);
//            //设置适配器
            gvShopType.setAdapter(adapter);
            //添加数据

        }

    }
}
