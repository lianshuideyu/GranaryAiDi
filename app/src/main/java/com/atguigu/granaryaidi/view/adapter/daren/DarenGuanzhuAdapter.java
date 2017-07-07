package com.atguigu.granaryaidi.view.adapter.daren;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenGuanzhuBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DarenGuanzhuAdapter extends BaseAdapter {

    private final Context context;
    private final List<DaRenGuanzhuBean.DataBean.ItemsBean.UsersBean> goods;

    public DarenGuanzhuAdapter(Context context, List<DaRenGuanzhuBean.DataBean.ItemsBean.UsersBean> goods) {
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

            convertView = View.inflate(context, R.layout.daren_guanzhu_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        /**
         * 设置数据
         */
        DaRenGuanzhuBean.DataBean.ItemsBean.UsersBean usersBean = goods.get(position);

        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .error(R.drawable.brand_bg)
                .placeholder(R.drawable.brand_bg)
                .into(viewHolder.ivGuanzhu);

        viewHolder.tvGuanzhu.setText(usersBean.getUser_name());
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_guanzhu)
        ImageView ivGuanzhu;
        @InjectView(R.id.tv_guanzhu)
        TextView tvGuanzhu;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
