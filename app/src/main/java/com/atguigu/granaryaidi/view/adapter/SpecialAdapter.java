package com.atguigu.granaryaidi.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopSpecialBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class SpecialAdapter extends BaseAdapter {

    private final List<ShopSpecialBean.DataBean.ItemsBean> items;
    private final Context context;

    public SpecialAdapter(Context context, List<ShopSpecialBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? null : items.size();
    }

    @Override
    public ShopSpecialBean.DataBean.ItemsBean getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.special_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        ShopSpecialBean.DataBean.ItemsBean itemsBean = items.get(position);

        viewHolder.tvShopSpecial.setText(itemsBean.getTopic_name());

        Glide.with(context)
                .load(itemsBean.getCover_img())
                .error(R.drawable.brand_bg)
                .placeholder(R.drawable.brand_bg)
                .into(viewHolder.ivShopSpecial);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_shop_special)
        ImageView ivShopSpecial;
        @InjectView(R.id.tv_shop_special)
        TextView tvShopSpecial;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
