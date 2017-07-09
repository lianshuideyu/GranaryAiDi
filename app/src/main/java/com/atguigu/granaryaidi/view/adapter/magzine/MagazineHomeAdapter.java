package com.atguigu.granaryaidi.view.adapter.magzine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.MagazineProductionItemBean;
import com.atguigu.granaryaidi.utils.DateChange;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MagazineHomeAdapter extends RecyclerView.Adapter<MagazineHomeAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<ArrayList<MagazineProductionItemBean>> beans;

    private final ArrayList<MagazineProductionItemBean> beanitems;
    private final String[] keys;

    public MagazineHomeAdapter(Context context, ArrayList<ArrayList<MagazineProductionItemBean>> beans,
                               ArrayList<MagazineProductionItemBean> beanitems, String[] keys) {
        this.context = context;
        this.beans = beans;
        this.beanitems = beanitems;
        this.keys = keys;

    }


    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < beans.size(); i++) {
            count += beans.get(i).size();

        }

        return count;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.setData(beanitems.get(position), position);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.magazine_home_item, parent, false);

        return new MyViewHolder(view);
    }

    private String temComparedate = null;
//    private MyViewHolder temHolder;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final View itemview;
        @InjectView(R.id.iv_magazine_home)
        ImageView ivMagazineHome;
        @InjectView(R.id.tv_magazine_home)
        TextView tvMagazineHome;
        @InjectView(R.id.tv_dates)
        TextView tvDates;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.itemview = itemView;

        }

        public void setData(MagazineProductionItemBean bean, final int position) {

            tvMagazineHome.setText(bean.getTopic_name());
            Glide.with(context)
                    .load(bean.getCover_img())
                    .error(R.drawable.brand_bg)
                    .placeholder(R.drawable.brand_bg)
                    .into(this.ivMagazineHome);
            String addtime = bean.getAddtime();
            String month = addtime.substring(5, 7);
            String day = addtime.substring(8, 10);

            String englishMon = DateChange.dateFormat(month);
            String date = "—— " + englishMon + "." + day + " ——";

            String comparedate = addtime.substring(5, 10);//为了与下一个日期比较使用的-->06-27
//            Log.d("comparedate", "date:== " + comparedate);
            /**达到相同日期只显示一个的目的
             * 拿到下一个bean对象的日期数据
             */

            if (position < beanitems.size() - 1) { //除去最后一个
                //拿到下一个的对象
                String behindthetime = beanitems.get(position + 1).getAddtime();
                String substring = behindthetime.substring(5, 10);
                String mon = behindthetime.substring(5, 7);
                String da = behindthetime.substring(8, 10);

                if (month.equals(mon) && day.equals(da)) {
                    tvDates.setVisibility(View.GONE);//如果和后边的日期一样则隐藏当前的
                } else {
                    tvDates.setVisibility(View.VISIBLE);
                }
            }


            tvDates.setText(date);

            /**
             *点击事件
             */
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });

        }
    }


    /**
     * item的点击事件用接口
     */
    public interface OnItemClickMagazineListener {

        void onItemClick(int position);

    }

    private OnItemClickMagazineListener listener;

    public void setOnItemClickMagazListener(OnItemClickMagazineListener l) {
        this.listener = l;
    }

}
