package com.atguigu.granaryaidi.view.adapter.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        @InjectView(R.id.tv_pinglun_name1)
        TextView tvPinglunName1;
        @InjectView(R.id.tv_pinglun_content1)
        TextView tvPinglunContent1;
        @InjectView(R.id.ll_pinglun_one)
        LinearLayout llPinglunOne;
        @InjectView(R.id.tv_pinglun_name2)
        TextView tvPinglunName2;
        @InjectView(R.id.tv_pinglun_content2)
        TextView tvPinglunContent2;
        @InjectView(R.id.ll_pinglun_two)
        LinearLayout llPinglunTwo;
        @InjectView(R.id.tv_pinglun_name3)
        TextView tvPinglunName3;
        @InjectView(R.id.tv_pinglun_content3)
        TextView tvPinglunContent3;
        @InjectView(R.id.ll_pinglun_three)
        LinearLayout llPinglunThree;

        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this,itemView);
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

            tvPinglunName1.setText(top_comments.get(0).getU().getName() + ": ");
            tvPinglunContent1.setText(top_comments.get(0).getContent());
            if(top_comments.size() > 1) {
                llPinglunTwo.setVisibility(View.VISIBLE);

                tvPinglunName2.setText(top_comments.get(1).getU().getName() + ": ");
                tvPinglunContent2.setText(top_comments.get(1).getContent());
            }
            
            if(top_comments.size() > 2) {
                llPinglunThree.setVisibility(View.VISIBLE);

                tvPinglunName3.setText(top_comments.get(2).getU().getName() + ": ");
                tvPinglunContent3.setText(top_comments.get(2).getContent());
            }
        }
    }
}
