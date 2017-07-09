package com.atguigu.granaryaidi.view.adapter.magzine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineAuthorListBean;
import com.atguigu.granaryaidi.view.utils.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MagazineAuthorAdapter extends BaseAdapter {

    private final List<MagazineAuthorListBean.DataBean.ItemsBean> items;
    private final Context context;

    //加载图标圆形图片用
    private RequestManager glideRequest;

    public MagazineAuthorAdapter(Context context, List<MagazineAuthorListBean.DataBean.ItemsBean> items) {
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

            convertView = View.inflate(context, R.layout.magazine_author_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        MagazineAuthorListBean.DataBean.ItemsBean itemsBean = items.get(position);

        viewHolder.tvBrandname.setText(itemsBean.getAuthor_name());
        viewHolder.tvBrandnote.setText(itemsBean.getNote());

//        Glide.with(context)
//                .load(itemsBean.getThumb())
//                .error(R.drawable.icon_large)
//                .placeholder(R.drawable.icon_large)
//                .into(viewHolder.ivBrandLogo);

        glideRequest = Glide.with(context);

        glideRequest.load(itemsBean.getThumb())
                .transform(new GlideCircleTransform(context)).into(viewHolder.ivBrandLogo);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_brand_logo)
        ImageView ivBrandLogo;
        @InjectView(R.id.tv_brandname)
        TextView tvBrandname;
        @InjectView(R.id.tv_brandnote)
        TextView tvBrandnote;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
