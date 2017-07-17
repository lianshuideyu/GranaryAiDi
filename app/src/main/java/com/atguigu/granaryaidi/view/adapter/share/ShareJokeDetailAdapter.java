package com.atguigu.granaryaidi.view.adapter.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokeDetailBean;
import com.atguigu.granaryaidi.bean.baisi.BaisiJokePinglunBean;
import com.atguigu.granaryaidi.view.utils.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ShareJokeDetailAdapter extends RecyclerView.Adapter<ShareJokeDetailAdapter.BaseViewHolder> {


    private BaisiJokeDetailBean jokebean;
    private List<BaisiJokePinglunBean.NormalBean.ListBean> hotlist;
    private Context context;
    private List<BaisiJokePinglunBean.NormalBean.ListBean> normallist;

    /**
     * 头布局
     */
    private static final int TYPE_HEAD = 0;

    /**
     * 评论部分
     */
    private static final int TYPE_NORMAL = 1;
    private List<BaisiJokePinglunBean.NormalBean.ListBean> allList;

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;

        //根据位置，从列表中得到一个数据对象
        if (position == 0) {
            itemViewType = TYPE_HEAD;
        } else {
            itemViewType = TYPE_NORMAL;
        }
        return itemViewType;
    }

    public void setHotlist(List<BaisiJokePinglunBean.NormalBean.ListBean> hotlist) {
        this.hotlist = hotlist;
    }

    public ShareJokeDetailAdapter(Context context, List<BaisiJokePinglunBean.NormalBean.ListBean> normallist,
                                  BaisiJokeDetailBean jokebean) {
        this.context = context;
        this.normallist = normallist;
        this.jokebean = jokebean;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case TYPE_HEAD:
                baseViewHolder = new HeadHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_head_item, parent, false));
                Log.e("TAG", "onCreateViewHolder---TYPE_HEAD");

                break;
            case TYPE_NORMAL:
                baseViewHolder = new NormalHolder(LayoutInflater.from(context)
                        .inflate(R.layout.all_normal_item, parent, false));
                break;
        }


        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_HEAD:
                HeadHolder headHolder = (HeadHolder) holder;

                headHolder.setData(jokebean);

                break;
            case TYPE_NORMAL:
                NormalHolder normalHolder = (NormalHolder) holder;

                normalHolder.setData(position);

                break;
        }

    }

    @Override
    public int getItemCount() {

//        if(hotlist != null) {
//            return 1 + hotlist.size();
//        }else if(normallist != null ) {
//            return 1 + normallist.size();
//        }else if(normallist != null && hotlist != null) {
//            return 1 + normallist.size() + hotlist.size();
//        }

        if(allList != null) {
            return allList.size() + 1;
        }
        return 1;
    }

    public void setAllList(List<BaisiJokePinglunBean.NormalBean.ListBean> allList) {
        this.allList = allList;
    }


    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    //加载图标圆形图片用
    private RequestManager glideRequest;

    class HeadHolder extends BaseViewHolder {
        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @InjectView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @InjectView(R.id.tv_posts_number)
        TextView tvPostsNumber;
        @InjectView(R.id.tv_download_number)
        TextView tvDownloadNumber;
        @InjectView(R.id.ll_pinglun)
        LinearLayout ll_pinglun;

        public HeadHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);

            ll_pinglun.setVisibility(View.GONE);
        }

        public void setData(BaisiJokeDetailBean jokebean) {
            tvName.setText(jokebean.getName());
            glideRequest = Glide.with(context);

            glideRequest.load(jokebean.getIcon())
                    .placeholder(R.drawable.brand_logo_empty)
                    .error(R.drawable.brand_logo_empty)
                    .transform(new GlideCircleTransform(context)).into(ivHeadpic);

            tvTimeRefresh.setText(jokebean.getUpdate());
            tvContent.setText(jokebean.getJokecontent());
            tvShenheDingNumber.setText(jokebean.getUpCount());
            tvShenheCaiNumber.setText(jokebean.getDownCount() + "");
            tvPostsNumber.setText(jokebean.getForwardCount() + "");
        }
    }

    class NormalHolder extends BaseViewHolder {
        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_fans_count)
        TextView tvFansCount;
        @InjectView(R.id.iv_preson_sex)
        ImageView ivPresonSex;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @InjectView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @InjectView(R.id.tv_pingluncontent)
        TextView tvPingluncontent;
        @InjectView(R.id.tv_hotping)
        TextView tv_hotping;
        @InjectView(R.id.tv_newping)
        TextView tv_newping;


        public NormalHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }


        public void setData(int position) {
            if(allList.size() > 0) {
                //此时才表明有数据
                if(hotlist != null && hotlist.size() > 0 ) {
                    //表示有热评
                    if(position == 1) {
                        tv_hotping.setVisibility(View.VISIBLE);
                    }else {
                        tv_hotping.setVisibility(View.GONE);
                    }

                    if(normallist != null && normallist.size() > 0 ) {
                        if(position == hotlist.size()+1) {
                            tv_newping.setVisibility(View.VISIBLE);
                        }else {
                            tv_newping.setVisibility(View.GONE);
                        }
                    }
                }else {
                    //没有热评,只有新评
                    if(normallist != null && normallist.size() > 0 ) {
                        if(position == 1) {
                            tv_newping.setVisibility(View.VISIBLE);
                        }else {
                            tv_newping.setVisibility(View.GONE);
                        }
                    }
                }

                glideRequest = Glide.with(context);
                BaisiJokePinglunBean.NormalBean.ListBean bean = allList.get(position - 1);

                glideRequest.load(bean.getUser().getProfile_image())
                        .placeholder(R.drawable.brand_logo_empty)
                        .error(R.drawable.brand_logo_empty)
                        .transform(new GlideCircleTransform(context)).into(ivHeadpic);

//                tvFansCount.setText(bean.getUser().getUsername());
                tvName.setText(bean.getUser().getUsername());
                tvShenheDingNumber.setText(bean.getLike_count() + "");
                tvShenheCaiNumber.setText(bean.getHate_count() + "");
                tvPingluncontent.setText(bean.getContent());
            }

        }
    }


}
