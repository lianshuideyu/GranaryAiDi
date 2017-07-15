package com.atguigu.granaryaidi.cart;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.bumptech.glide.Glide;
import com.chaychan.library.ExpandableLinearLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class IndentDetailActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.ll_address)
    LinearLayout llAddress;
    @InjectView(R.id.ell_product)
    ExpandableLinearLayout ellProduct;
    @InjectView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @InjectView(R.id.tv_upprice)
    TextView tvUpprice;
    @InjectView(R.id.tv_pricec_total)
    TextView tv_pricec_total;
    @InjectView(R.id.tv_gotopay)
    TextView tv_gotopay;

    @InjectView(R.id.ell_payment)
    ExpandableLinearLayout ell_payment;

    private List<GoodsBean> goodsBeens;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showTotalPrice();

        tvTitle.setText("订单详情");
    }

    @Override
    public void initView() {
        ibShopBack.setVisibility(View.VISIBLE);

        ellProduct.removeAllViews();//清除所有的子View（避免重新刷新数据时重复添加）
        ell_payment.removeAllViews();

        goodsBeens = CartStorage.getInstance(this).getAllData();

        //添加数据
        for (int i = 0; i < goodsBeens.size(); i++) {
            View view = View.inflate(this, R.layout.item_product_two, null);

//            GoodsBean productBean = new GoodsBean(imgUrls[i], names[i], intros[i], "12.00");
            ViewHolder viewHolder = new ViewHolder(view, goodsBeens.get(i));
            viewHolder.refreshUI();
            ellProduct.addItem(view);//添加子条目
        }

        View view2 = View.inflate(this, R.layout.item_product_paymode, null);
        ell_payment.addItem(view2);
    }

    public void showTotalPrice() {
        tvTotalprice.setText("总价 ： ￥" + getTotalPrice());
        tv_pricec_total.setText("￥" + getTotalPrice());

    }

    private double getTotalPrice() {
        double result = 0;
        if (goodsBeens != null && goodsBeens.size() > 0) {
            for (int i = 0; i < goodsBeens.size(); i++) {
                GoodsBean goodsBean = goodsBeens.get(i);
                //判断是否勾选
                if (goodsBean.isChecked()) {
                    result = result + goodsBean.getNumber() * Double.parseDouble(goodsBean.getCover_price());
                }
            }
        }
        return result;
    }

    class ViewHolder {
        @InjectView(R.id.iv_brand_logo)
        ImageView iv_brand_logo;
        @InjectView(R.id.tv_desc_gov)
        TextView tv_desc_gov;
        @InjectView(R.id.tv_product_type)
        TextView tv_product_type;
        @InjectView(R.id.tv_price)
        TextView tvPrice;
        @InjectView(R.id.tv_product_count)
        TextView tv_product_count;

        GoodsBean productBean;

        public ViewHolder(View view, GoodsBean productBean) {
            ButterKnife.inject(this, view);
            this.productBean = productBean;
        }

        private void refreshUI() {
            Glide.with(IndentDetailActivity.this)
                    .load(productBean.getFigure())
                    .placeholder(R.drawable.brand_logo_empty)
                    .into(iv_brand_logo);
            tv_desc_gov.setText(productBean.getName());
            tv_product_type.setText(productBean.getType());
            tvPrice.setText("￥" + productBean.getCover_price());
            tv_product_count.setText("x" + productBean.getNumber());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_indent_detail;
    }


    @OnClick({R.id.ib_shop_back, R.id.ll_address , R.id.tv_gotopay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                finish();
                break;
            case R.id.ll_address:
                showToast("请输入地址");
                break;
            case R.id.tv_gotopay:
                showToast("付款");
                break;
        }
    }


}
