package com.atguigu.granaryaidi.view.adapter.share;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

        @InjectView(R.id.rl_click)
        RelativeLayout rl_click;
        @InjectView(R.id.tv_close)
        TextView tv_close;
        @InjectView(R.id.tv_open)
        TextView tv_open;



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

            setTextListener();
        }

        public void setTextListener(){

            int lineHeight = tvJokeContent.getLineHeight();
            int measuredHeight = tvJokeContent.getMeasuredHeight();
            int allHeight = measureTextViewHeight(tvJokeContent.getText().toString(), 13,
                    measuredHeight);
            DisplayMetrics dm = new DisplayMetrics();

            dm = context.getResources().getDisplayMetrics();
            float screenW = dm.widthPixels;
            float paddingLeft = tvJokeContent.getPaddingLeft();
            float paddingReft = tvJokeContent.getPaddingRight();

            int count = (int) Math
                    .ceil((tvJokeContent.getPaint().measureText(tvJokeContent.getText().toString()) / (screenW
                            - paddingLeft - paddingReft - 159)));

            // 计算行数
            if (allHeight % lineHeight > 0) {
                count = allHeight / lineHeight + 1;
            } else {
                count = allHeight / lineHeight;
            }

            if (count > 5) {
                tvJokeContent.setMaxLines(5);
                tv_close.setVisibility(View.GONE);
                tv_open.setVisibility(View.VISIBLE);

            } else {
                tvJokeContent.setMaxLines(999);
                tv_open.setVisibility(View.GONE);
                tv_close.setVisibility(View.GONE);

            }
            rl_click.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (tv_open.getVisibility() == View.VISIBLE) {
                        tvJokeContent.setMaxLines(999);

                        tv_open.setVisibility(View.GONE);
                        tv_close.setVisibility(View.VISIBLE);
                    } else {
                        tvJokeContent.setMaxLines(5);
                        tv_close.setVisibility(View.GONE);
                        tv_open.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }

    // 计算TextView的高度
    private int measureTextViewHeight(String text, int textSize, int deviceWidth) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth,
                View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }
}
