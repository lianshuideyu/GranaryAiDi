package com.atguigu.granaryaidi.view.adapter.share;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokeBean;
import com.atguigu.granaryaidi.view.utils.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ShareJokeAdapter extends RecyclerView.Adapter<ShareJokeAdapter.MyViewHolder> {

    private final Context context;
    private final List<BaisiJokeBean.ListBean> lists;


    public ShareJokeAdapter(Context context, List<BaisiJokeBean.ListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.share_joke_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(lists.get(position), position);

    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    //加载图标圆形图片用
    private RequestManager glideRequest;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_user_icon)
        ImageView ivUserIcon;
        @InjectView(R.id.tv_user_name)
        TextView tvUserName;
        @InjectView(R.id.tv_updatetime)
        TextView tvUpdatetime;
        @InjectView(R.id.tv_joke_content)
        TextView tvJokeContent;
        @InjectView(R.id.tv_zan)
        TextView tvZan;
        @InjectView(R.id.tv_down_joke)
        TextView tvDown;
        @InjectView(R.id.tv_share_joke)
        TextView tvShare;
        @InjectView(R.id.tv_pinglun_joke)
        TextView tvPinglun;

        @InjectView(R.id.tv_pinglun_content1)
        TextView tvPinglunContent1;
        @InjectView(R.id.tv_pinglun_content2)
        TextView tvPinglunContent2;
        @InjectView(R.id.tv_pinglun_content3)
        TextView tvPinglunContent3;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            tvDown = (TextView) itemView.findViewById(R.id.tv_down_joke);
        }

        public void setData(BaisiJokeBean.ListBean bean, int position) {
//            Glide.with(context)
//                    .load(listBean.getU().getHeader().get(0))
//                    .into(ivUserIcon);

            glideRequest = Glide.with(context);

            glideRequest.load(bean.getU().getHeader().get(0))
                    .placeholder(R.drawable.brand_logo_empty)
                    .error(R.drawable.brand_logo_empty)
                    .transform(new GlideCircleTransform(context)).into(ivUserIcon);

            tvUserName.setText(bean.getU().getName());

            tvUpdatetime.setText(bean.getPasstime());
            tvJokeContent.setText(bean.getText());
            tvZan.setText(bean.getUp());
//            tvDown.setText(bean.getDown());
//            tvShare.setText(bean.getForward());
//            tvPinglun.setText(bean.getComment());

            //设置第一条评论的消息
            List<BaisiJokeBean.ListBean.TopCommentsBean> top_comments = bean.getTop_comments();
            if (top_comments != null && top_comments.size() > 0) {

                BaisiJokeBean.ListBean.TopCommentsBean bean2 = top_comments.get(0);
                tvPinglunContent1.setText(bean2.getU().getName() + ": " + bean2.getContent());
                //使名字变色
                SpannableStringBuilder builder = new SpannableStringBuilder(tvPinglunContent1.getText().toString());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                builder.setSpan(colorSpan, 0, bean2.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvPinglunContent1.setText(builder);

                if (top_comments.size() > 1) {
                    tvPinglunContent2.setVisibility(View.VISIBLE);

                    BaisiJokeBean.ListBean.TopCommentsBean bean3 = top_comments.get(1);
                    tvPinglunContent2.setText(bean3.getU().getName() + ": " + bean3.getContent());
                    //使名字变色
                    SpannableStringBuilder builder3 = new SpannableStringBuilder(tvPinglunContent2.getText().toString());
                    ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                    builder3.setSpan(colorSpan3, 0, bean3.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvPinglunContent2.setText(builder3);
                }

                if (top_comments.size() > 2) {
                    tvPinglunContent3.setVisibility(View.VISIBLE);

                    BaisiJokeBean.ListBean.TopCommentsBean bean4 = top_comments.get(2);
                    tvPinglunContent3.setText(bean4.getU().getName() + ": " + bean4.getContent());
                    //使名字变色
                    SpannableStringBuilder builder3 = new SpannableStringBuilder(tvPinglunContent3.getText().toString());
                    ForegroundColorSpan colorSpan3 = new ForegroundColorSpan(Color.parseColor("#1f83a4"));
                    builder3.setSpan(colorSpan3, 0, bean4.getU().getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvPinglunContent3.setText(builder3);
                }
            }
        }
    }
}
