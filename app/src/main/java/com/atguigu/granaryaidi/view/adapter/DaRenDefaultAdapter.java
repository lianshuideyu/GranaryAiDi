package com.atguigu.granaryaidi.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenDefaultBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class DaRenDefaultAdapter extends BaseAdapter {

    private final List<DaRenDefaultBean.DataBean.ItemsBean> items;
    private final Context context;

    public DaRenDefaultAdapter(Context context, List<DaRenDefaultBean.DataBean.ItemsBean> items) {
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

            convertView = View.inflate(context, R.layout.darendefault_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        DaRenDefaultBean.DataBean.ItemsBean bean = items.get(position);

        viewHolder.tvDarenName.setText(bean.getUsername());
        viewHolder.tvDarenType.setText(bean.getDuty());


        Glide.with(context)
                .load(bean.getUser_images().getOrig())
                .error(R.drawable.ic_default_good)
                .placeholder(R.drawable.ic_default_good)
                .into(viewHolder.ivDarenIcon);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_daren_icon)
        ImageView ivDarenIcon;
        @InjectView(R.id.tv_daren_name)
        TextView tvDarenName;
        @InjectView(R.id.tv_daren_type)
        TextView tvDarenType;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
