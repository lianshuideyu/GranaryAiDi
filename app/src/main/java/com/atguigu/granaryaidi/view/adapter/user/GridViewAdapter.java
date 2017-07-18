package com.atguigu.granaryaidi.view.adapter.user;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.bilibili.BiliLiveBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/18.
 */

public class GridViewAdapter extends BaseAdapter {

    private final List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives;
    private final Context context;

    public GridViewAdapter(Context context, List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.context = context;
        this.lives = lives;
        Log.e("size","size===" + lives.size());
    }

    @Override
    public int getCount() {
        return lives.size();
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

            convertView = View.inflate(context, R.layout.zhibogridview_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        BiliLiveBean.DataBean.PartitionsBean.LivesBean bean = lives.get(position);


        Glide.with(context)
                .load(bean.getCover().getSrc())
                .error(R.drawable.brand_bg)
                .placeholder(R.drawable.brand_bg)
                .into(viewHolder.ivGridview);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_gridview)
        ImageView ivGridview;
        @InjectView(R.id.tv_gridview_title)
        TextView tvGridviewTitle;
        @InjectView(R.id.tv_shop_pinpai)
        TextView tvShopPinpai;
        @InjectView(R.id.tv_likecount)
        TextView tvLikecount;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
