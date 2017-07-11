package com.atguigu.granaryaidi.cart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.cart.GoodsBean;
import com.atguigu.granaryaidi.view.viewmyself.AddSubViewTwo;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CartShopAdapter extends BaseAdapter {

    private final List<GoodsBean> products;
    private final Context context;
    private final CheckBox cbAllIscheck;
    private final TextView tvPriceAll;

    private boolean iseditCart = false;

    public void setisEdit(boolean iseditCart) {
        this.iseditCart = iseditCart;
    }

    public CartShopAdapter(Context context, List<GoodsBean> products, CheckBox cbAllIscheck, TextView tvPriceAll) {
        this.context = context;
        this.products = products;

        this.cbAllIscheck = cbAllIscheck;
        this.tvPriceAll = tvPriceAll;
        showTotalPrice();
    }

    public void showTotalPrice() {
        tvPriceAll.setText("总价 ： ￥"+getTotalPrice());

    }

    private double getTotalPrice() {
        double result = 0;
        if(products != null && products.size() > 0){
            for(int i = 0; i < products.size(); i++) {
                GoodsBean goodsBean = products.get(i);
                //判断是否勾选
                if(goodsBean.isChecked()){
                    result = result + goodsBean.getNumber()* Double.parseDouble(goodsBean.getCover_price());
                }
            }
        }
        return result;
    }

    @Override
    public int getCount() {
        return products == null ? 0 : products.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GoodsBean bean = products.get(position);

        if (!iseditCart) {
            /**
             * 显示为编辑的布局
             */

            ViewHolder viewHolder = null;
            if (convertView == null) {

                convertView = View.inflate(context, R.layout.cart_shop_item, null);
                viewHolder = new ViewHolder(convertView, position);

                convertView.setTag(viewHolder);
            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
                convertView = View.inflate(context, R.layout.cart_shop_item, null);
                viewHolder = new ViewHolder(convertView, position);
            }

            viewHolder.cbProduct.setChecked(bean.isChecked());//bean默认是为true的

            viewHolder.tvDescGov.setText(bean.getName());
            viewHolder.tvProductType.setText(bean.getType());
            viewHolder.tvPrice.setText(bean.getCover_price());
            //这里注意number 为int类型的
            viewHolder.tv_product_count.setText("x" + bean.getNumber());

            Glide.with(context)
                    .load(bean.getFigure())
                    .error(R.drawable.icon_large)
                    .placeholder(R.drawable.icon_large)
                    .into(viewHolder.ivBrandLogo);

            return convertView;
        } else {
            /**
             * 显示为编辑的布局
             */

            ViewHolderTwo viewHolder = null;
            if (convertView == null) {

                convertView = View.inflate(context, R.layout.cart_shop_edit_item, null);
                viewHolder = new ViewHolderTwo(convertView, position);

                convertView.setTag(viewHolder);
            } else {
//                viewHolder = (ViewHolderTwo) convertView.getTag();
                convertView = View.inflate(context, R.layout.cart_shop_edit_item, null);
                viewHolder = new ViewHolderTwo(convertView, position);
            }

            viewHolder.cbEditProduct.setChecked(bean.isChecked());//bean默认是为true的

            viewHolder.tvEditName.setText(bean.getName());
            viewHolder.tvEditPrice.setText(bean.getCover_price());

            Glide.with(context)
                    .load(bean.getFigure())
                    .error(R.drawable.icon_large)
                    .placeholder(R.drawable.icon_large)
                    .into(viewHolder.ivEditbrandLogo);

            return convertView;
        }
    }


    class ViewHolder {
        @InjectView(R.id.cb_product)
        CheckBox cbProduct;
        @InjectView(R.id.iv_brand_logo)
        ImageView ivBrandLogo;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_product_type)
        TextView tvProductType;
        @InjectView(R.id.tv_price)
        TextView tvPrice;
        @InjectView(R.id.tv_priceold)
        TextView tvPriceold;
        @InjectView(R.id.rl_oldprice)
        RelativeLayout rlOldprice;
        @InjectView(R.id.tv_product_count)
        TextView tv_product_count;

        ViewHolder(View itemView, final int position) {
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //状态取反
                    GoodsBean bean = products.get(position);

                    bean.setChecked(!bean.isChecked());

                    //刷新适配器notifyItemChanged(getLayoutPosition());
                    notifyDataSetChanged();

                    //重新显示总价格
                    showTotalPrice();
                }
            });
        }
    }

    class ViewHolderTwo {
        @InjectView(R.id.cb_edit_product)
        CheckBox cbEditProduct;
        @InjectView(R.id.iv_editbrand_logo)
        ImageView ivEditbrandLogo;
        @InjectView(R.id.edit_goodinfo_num)
        AddSubViewTwo editGoodinfoNum;
        @InjectView(R.id.tv_edit_name)
        TextView tvEditName;
        @InjectView(R.id.tv_edit_price)
        TextView tvEditPrice;
        @InjectView(R.id.tv_edit_delete)
        TextView tvEditDelete;

        ViewHolderTwo(View view, final int position) {
            ButterKnife.inject(this, view);

            /**
             * 点击事件，点击删除
             */
            tvEditDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击删除
                    Log.e("cart", "删除商品");

                }
            });

            cbEditProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //状态取反
                    GoodsBean bean = products.get(position);

                    bean.setChecked(!bean.isChecked());

                    //刷新适配器notifyItemChanged(getLayoutPosition());
                    notifyDataSetChanged();

                    //重新显示总价格
                    showTotalPrice();
                }
            });
        }
    }
}
