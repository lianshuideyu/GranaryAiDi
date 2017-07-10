package com.atguigu.granaryaidi.view.adapter.magzine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atguigu.granaryaidi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/10.
 */

public class MagazineTypeAdapter extends BaseAdapter {

    private final Context context;

    public MagazineTypeAdapter(Context context) {
        this.context = context;
    }

    /**
     * 显示20条假数据
     * @return
     */
    @Override
    public int getCount() {
        return 20;
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

            convertView = View.inflate(context, R.layout.magazine_type_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_type)
        ImageView iv_type;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
