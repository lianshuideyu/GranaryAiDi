package com.atguigu.granaryaidi.view.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import com.atguigu.granaryaidi.utils.DensityUtil;
import com.atguigu.granaryaidi.view.viewmyself.AddSubView;
import com.bumptech.glide.Glide;

import java.util.List;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
            //第一行的类型先设置了后边动态new
            tvProductType.setText(items.getSku_info().get(0).getType_name());

//            设置可购买的类型
            setllProductTypeData();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setllProductTypeData() {

        //放第一种类型的数据
        LinearLayout linearLayout1 = getTypeLinearLayout(0);

        llProductType.addView(linearLayout1);
        /**
         * 设置第二行以后的类型数据
         */
        if(items.getSku_info().size() > 1) {

            for(int i = 1; i < items.getSku_info().size(); i++) {

                LinearLayout typeLinearLayout = getTypeLinearLayout(i);



                llProductType.addView(typeLinearLayout);
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    private LinearLayout getTypeLinearLayout(int id) {
        //设置每行类型的标题
        if(id != 0) {
            TextView text1 = new TextView(GoodsGotoBuyActivity.this);
            LinearLayout.LayoutParams lpp1 = new LinearLayout.LayoutParams(//字体高20sp
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            text1.setLayoutParams(lpp1);
            lpp1.setMargins(0,10,0,10);

            text1.setTextColor(Color.parseColor("#C5C5C5"));
            text1.setTextSize(15);
            text1.setText(items.getSku_info().get(id).getType_name());
//                                        textView2.setPadding(0,DensityUtil.dip2px(GoodsDetailsActivity.this,10),0,DensityUtil.dip2px(GoodsDetailsActivity.this,10));
            llProductType.addView(text1);
        }


        //设置具体没行内容----------------------------------------
        LinearLayout linearLayout1 = new LinearLayout(this);
        LinearLayout.LayoutParams lp1 = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setLayoutDirection(LinearLayout.HORIZONTAL);
        lp1.setMargins(0, 10, 0, 0);
        linearLayout1.setLayoutParams(lp1);
        for(int i = 0; i < items.getSku_info().get(id).getAttrList().size(); i++) {
            List<GoodsDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList
                    = items.getSku_info().get(id).getAttrList();

            TextView text = new TextView(GoodsGotoBuyActivity.this);
            LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(//字体高20sp
                    LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(GoodsGotoBuyActivity.this,30));
            text.setBackgroundColor(Color.parseColor("#424950"));
            text.setPadding(10,10,10,10);
            lpp.setMargins(0, 0, 10, 0);//右边距为10dp
            text.setLayoutParams(lpp);

            text.setTextColor(Color.WHITE);
            text.setTextSize(12);
            text.setText(attrList.get(i).getAttr_name());
//                                        textView2.setPadding(0,DensityUtil.dip2px(GoodsDetailsActivity.this,10),0,DensityUtil.dip2px(GoodsDetailsActivity.this,10));
            linearLayout1.addView(text);
        }
        return linearLayout1;
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
