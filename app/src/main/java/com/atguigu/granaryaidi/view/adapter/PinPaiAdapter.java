package com.atguigu.granaryaidi.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class PinPaiAdapter extends BaseAdapter {

    private final List<ShopPinPaiBean.DataBean.ItemsBean> items;
    private final Context context;

    public PinPaiAdapter(Context context, List<ShopPinPaiBean.DataBean.ItemsBean> items) {
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

            convertView = View.inflate(context, R.layout.pinpai_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        ShopPinPaiBean.DataBean.ItemsBean itemsBean = items.get(position);

        viewHolder.tvPinpainame.setText(itemsBean.getBrand_name());

        Glide.with(context)
                .load(itemsBean.getBrand_logo())
                .error(R.drawable.icon_large)
                .placeholder(R.drawable.icon_large)
                .into(viewHolder.tvBrandLogo);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_brand_logo)
        ImageView tvBrandLogo;
        @InjectView(R.id.tv_pinpainame)
        TextView tvPinpainame;
        @InjectView(R.id.iv_pinpai_arrow)
        ImageView ivPinpaiArrow;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
