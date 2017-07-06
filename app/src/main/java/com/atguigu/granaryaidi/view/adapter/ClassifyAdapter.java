package com.atguigu.granaryaidi.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopClassifBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ClassifyAdapter extends BaseAdapter {

    private final List<ShopClassifBean.DataBean.ItemsBean> items;
    private final Context context;

    public ClassifyAdapter(Context content, List<ShopClassifBean.DataBean.ItemsBean> items) {

        this.context = content;
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

            convertView = View.inflate(context, R.layout.classify_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        String img = items.get(position).getNew_cover_img();

        Glide.with(context)
                .load(img)
                .into(viewHolder.ivClassify);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_classify)
        ImageView ivClassify;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
