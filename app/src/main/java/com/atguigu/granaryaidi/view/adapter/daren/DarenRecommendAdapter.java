package com.atguigu.granaryaidi.view.adapter.daren;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenRecommendBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenRecommendAdapter extends BaseAdapter {

    private final Context context;
    private final List<DaRenRecommendBean.DataBean.ItemsBean.GoodsBean> goods;

    public DarenRecommendAdapter(Context context, List<DaRenRecommendBean.DataBean.ItemsBean.GoodsBean> goods) {
        this.context = context;
        this.goods = goods;
    }

    @Override
    public int getCount() {
        return goods == null ? null : goods.size();
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

            convertView = View.inflate(context, R.layout.daren_recommend_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        /**
         * 设置数据
         */
        DaRenRecommendBean.DataBean.ItemsBean.GoodsBean goodsBean = goods.get(position);


        Glide.with(context)
                .load(goodsBean.getGoods_image())
                .error(R.drawable.brand_bg)
                .placeholder(R.drawable.brand_bg)
                .into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
