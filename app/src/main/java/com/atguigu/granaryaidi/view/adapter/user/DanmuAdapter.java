package com.atguigu.granaryaidi.view.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/21.
 */

public class DanmuAdapter extends BaseAdapter {


    private final List<String> danmus;
    private final Context context;

    public DanmuAdapter(Context context, List<String> danmus) {
        this.context = context;
        this.danmus = danmus;
    }

    @Override
    public int getCount() {
        return danmus.size();
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

            convertView = View.inflate(context, R.layout.danmu_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.tvDanmu.setText(danmus.get(position));

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_danmu)
        TextView tvDanmu;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
