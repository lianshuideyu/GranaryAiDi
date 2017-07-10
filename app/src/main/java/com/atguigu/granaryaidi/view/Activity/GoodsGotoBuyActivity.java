package com.atguigu.granaryaidi.view.Activity;

import android.os.Build;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.GoodsDetailsBean;
import com.atguigu.granaryaidi.view.viewmyself.AddSubView;
import com.bumptech.glide.Glide;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsGotoBuyActivity extends BaseActivity {


    @InjectView(R.id.brand_name)
    TextView brandName;
    @InjectView(R.id.tv_product_name)
    TextView tvProductName;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_product_type)
    TextView tvProductType;
    @InjectView(R.id.ll_product_type)
    LinearLayout llProductType;
    @InjectView(R.id.nas_goodinfo_num)
    AddSubView nasGoodinfoNum;
    @InjectView(R.id.iv_brand_buy_logo)
    ImageView ivBrandBuyLogo;
    @InjectView(R.id.tv_ensure)
    TextView tvEnsure;
    @InjectView(R.id.tv_dimis)
    TextView tvDimis;

    /**
     * 上层页面传来的数据
     *
     * 该页面打开的时候需要判断是从哪个按键被打开的，是“加入购物车”还是“直接购买”
     */
    private GoodsDetailsBean.DataBean.ItemsBean items;

    /**
     * 转场动画
     */
    @Override
    public void activityAnmotion() {
        super.activityAnmotion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //方法二,从下边进来
            Transition explode = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_bottom);
            getWindow().setEnterTransition(explode);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        /**
         * 设置数据
         */
        if(items != null) {
            Glide.with(GoodsGotoBuyActivity.this)
                    .load(items.getGoods_image())
                    .into(ivBrandBuyLogo);

            brandName.setText(items.getBrand_info().getBrand_name());
            tvProductName.setText(items.getGoods_name());
            if(TextUtils.isEmpty(items.getDiscount_price())) {
                tvPrice.setText(items.getPrice());
            }else {
                tvPrice.setText(items.getDiscount_price());
            }

            tvProductType.setText(items.getSku_info().get(0).getType_name());

//            llProductType
        }

    }

    @Override
    public void initView() {
        items = (GoodsDetailsBean.DataBean.ItemsBean) getIntent().getSerializableExtra("goodsbean");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_goto_buy;
    }


    @OnClick({R.id.tv_dimis, R.id.tv_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dimis:
                //用系统的返回方法
                onBackPressed();

                break;
            case R.id.tv_ensure:
                //点击确定加入购物车或者直接购买
                //然后页面返回后跳转...

                break;
        }
    }
}
