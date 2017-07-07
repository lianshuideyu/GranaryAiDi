package com.atguigu.granaryaidi.view.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiListBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PinpaiListAdapter extends BaseAdapter {

    private final List<ShopPinPaiListBean.DataBean.ItemsBean> items;
    private final Context context;


    public PinpaiListAdapter(Context context, List<ShopPinPaiListBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? null : items.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.classifylist_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        ShopPinPaiListBean.DataBean.ItemsBean bean = items.get(position);

        /**
         * 设置数据
         */

        Glide.with(context)
                .load(bean.getGoods_image())
                .error(R.drawable.brand_bg)
                .placeholder(R.drawable.brand_bg)
                .into(viewHolder.ivShop);

        viewHolder.tvShopTitle.setText(bean.getGoods_name());
        viewHolder.tvShopPinpai.setText(bean.getBrand_info().getBrand_name());
        viewHolder.tvLikecount.setText(bean.getLike_count());


        if(!TextUtils.isEmpty(bean.getPromotion_imgurl())) {
            //这是商品有折扣的情况
            viewHolder.tvShopPriceold.setVisibility(View.VISIBLE);
            viewHolder.view.setVisibility(View.VISIBLE);

            viewHolder.tvShopPrice.setText("￥" + bean.getDiscount_price());
            viewHolder.tvShopPriceold.setText("￥" + bean.getPrice());
            //折扣活动的图标设置
            Glide.with(context)
                    .load(bean.getPromotion_imgurl())
                    .into(viewHolder.ivShopPricedown);

        }else {

            viewHolder.tvShopPrice.setText("￥" + bean.getPrice());
        }
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_shop)
        ImageView ivShop;
        @InjectView(R.id.iv_shop_pricedown)
        ImageView ivShopPricedown;
        @InjectView(R.id.tv_shop_title)
        TextView tvShopTitle;
        @InjectView(R.id.tv_shop_pinpai)
        TextView tvShopPinpai;
        @InjectView(R.id.tv_shop_price)
        TextView tvShopPrice;
        @InjectView(R.id.tv_shop_priceold)
        TextView tvShopPriceold;
        @InjectView(R.id.tv_likecount)
        TextView tvLikecount;

        @InjectView(R.id.view)
        View view;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
