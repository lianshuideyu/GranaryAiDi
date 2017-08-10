package com.atguigu.granaryaidi.view.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.bilibili.BiliLiveBean;
import com.atguigu.granaryaidi.view.Activity.bili.LivePlayerActivity;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/18.
 */

public class BiliLiveAdapter extends RecyclerView.Adapter {


    private BiliLiveBean.DataBean data;
    private final Context context;

    /**
     * n种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道,分类
     */
    public static final int CHANNEL = 1;

    /**
     * 绘画专区
     */
    public static final int DRAWING_AREA = 2;

    /**
     * 生活娱乐
     */
    public static final int ENT_LIFE = 3;
    /**
     * 唱见舞见
     */
    public static final int SING_DANCE = 4;
    /**
     * 手游直播
     */
    public static final int MOBILE_GAME = 5;
    /**
     * 单机联机
     */
    public static final int SINGLE = 6;
    /**
     * 网络游戏
     */
    public static final int ONLINE = 7;
    /**
     * 电子竞技
     */
    public static final int E_SPORTS = 8;
    /**
     * 御宅文化
     */
    public static final int OTAKU = 9;

    /**
     * 放映厅
     */
    public static final int MOVIE = 10;



    public BiliLiveAdapter(Context context, BiliLiveBean.DataBean data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        int currentType = -1;

        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else {
            currentType = DRAWING_AREA;
        }
//        else if (position == ENT_LIFE) {
//            currentType = ENT_LIFE;
//        } else if (position == SING_DANCE) {
//            currentType = SING_DANCE;
//        } else if (position == MOBILE_GAME) {
//            currentType = MOBILE_GAME;
//        } else if (position == SINGLE) {
//            currentType = SINGLE;
//        } else if (position == ONLINE) {
//            currentType = ONLINE;
//        } else if (position == E_SPORTS) {
//            currentType = E_SPORTS;
//        } else if (position == OTAKU) {
//            currentType = OTAKU;
//        }

//        else if (position == MOVIE) {
//            currentType = MOVIE;
//        }

//        for(int i = 0; i < data.getPartitions().size(); i++) {
//            if(data.getPartitions().get(i).getPartition().getArea().equals("draw")) {
//                currentType = DRAWING_AREA;
//            }
//
//
//        }

        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.banner_viewpager, parent, false));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.channel_item, parent, false));
        } else if (viewType == DRAWING_AREA) {
            return new DrawingViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.drawing_item, parent, false));
        }

//        else if (viewType == ENT_LIFE) {
//            return new EntlifeViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == SING_DANCE) {
//            return new SingdanceViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == MOBILE_GAME) {
//            return new MobilegameViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == SINGLE) {
//            return new SingleViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == ONLINE) {
//            return new OnlineViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == E_SPORTS) {
//            return new EsportsViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        } else if (viewType == OTAKU) {
//            return new OtakuViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        }

//        else if (viewType == MOVIE) {
//            return new MovieViewHolder(LayoutInflater.from(context)
//                    .inflate(R.layout.drawing_item, parent, false));
//        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            if (data.getBanner() != null && data.getBanner().size() > 0) {
                bannerViewHolder.setData(data.getBanner());
            }

        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData();
        } else if (getItemViewType(position) == DRAWING_AREA) {
            DrawingViewHolder drawingViewHolder = (DrawingViewHolder) holder;
            drawingViewHolder.setData(position - 2);
        }

//        else if (getItemViewType(position) == ENT_LIFE) {
//            EntlifeViewHolder entlifeViewHolder = (EntlifeViewHolder) holder;
//            entlifeViewHolder.setData();
//        } else if (getItemViewType(position) == SING_DANCE) {
//            SingdanceViewHolder singdanceViewHolder = (SingdanceViewHolder) holder;
//            singdanceViewHolder.setData();
//        } else if (getItemViewType(position) == MOBILE_GAME) {
//            MobilegameViewHolder mobilegameViewHolder = (MobilegameViewHolder) holder;
//            mobilegameViewHolder.setData();
//        } else if (getItemViewType(position) == SINGLE) {
//            SingleViewHolder singleViewHolder = (SingleViewHolder) holder;
//            singleViewHolder.setData();
//        } else if (getItemViewType(position) == ONLINE) {
//            OnlineViewHolder onlineViewHolder = (OnlineViewHolder) holder;
//            onlineViewHolder.setData();
//        } else if (getItemViewType(position) == E_SPORTS) {
//            EsportsViewHolder esportsViewHolder = (EsportsViewHolder) holder;
//            esportsViewHolder.setData();
//        } else if (getItemViewType(position) == OTAKU) {
//            OtakuViewHolder otakuViewHolder = (OtakuViewHolder) holder;
//            otakuViewHolder.setData();
//        }


//        else if (getItemViewType(position) == MOVIE) {
//            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
//            movieViewHolder.setData();
//        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : 2 + data.getPartitions().size();
    }

    public void setData(BiliLiveBean.DataBean data) {
        this.data = data;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.bili_zhibo_banner)
        Banner biliZhiboBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData(List<BiliLiveBean.DataBean.BannerBean> banner) {

            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < banner.size(); i++) {

                images.add(banner.get(i).getImg());
            }
            //添加banner数据
            //设置图片加载器
            biliZhiboBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合

            biliZhiboBanner.setImages(images);
            //设置是否轮播
            biliZhiboBanner.isAutoPlay(true);
            //设置轮播时间
//                goodsBanner.setDelayTime(1500);
            //banner设置方法全部调用完毕时最后调用
            biliZhiboBanner.start();

        }
    }

    /**
     * 用于加载banner数据
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Picasso 加载图片简单用法
//            Picasso.with(context).load((String) path).into(imageView);

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }

    }

    class ChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.tv_channel_one)
        TextView tvChannelOne;
        @InjectView(R.id.tv_channel_two)
        TextView tvChannelTwo;
        @InjectView(R.id.tv_channel_three)
        TextView tvChannelThree;
        @InjectView(R.id.tv_channel_four)
        TextView tvChannelFour;
        @InjectView(R.id.tv_channel_five)
        TextView tvChannelFive;

        public ChannelViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {

            tvChannelOne.setOnClickListener(this);
            tvChannelTwo.setOnClickListener(this);
            tvChannelThree.setOnClickListener(this);
            tvChannelFour.setOnClickListener(this);
            tvChannelFive.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_channel_one:

                    Toast.makeText(context, "关注", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_channel_two:
                    Toast.makeText(context, "中心", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_channel_three:

                    Toast.makeText(context, "小视频", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_channel_four:
                    Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_channel_five:
                    Toast.makeText(context, "分类", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    class DrawingViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives;

        public DrawingViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData(int position) {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(position).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                lives = data.getPartitions().get(position).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                    setListener();

                    //关闭GridView的滚动事件
                    gvDrawing.setOverScrollMode(View.OVER_SCROLL_NEVER);
                }


            }

        }

        private void setListener() {
            gvDrawing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent(context, LivePlayerActivity.class);
                    intent.putExtra("playurl",lives.get(position).getPlayurl());
                    intent.putExtra("face",lives.get(position).getOwner().getFace());//头像
                    intent.putExtra("name",lives.get(position).getOwner().getName());
                    intent.putExtra("online",lives.get(position).getOnline());
                    intent.putExtra("title",lives.get(position).getTitle());

                    context.startActivity(intent);
                }
            });
        }
    }

    class EntlifeViewHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public EntlifeViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(1).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(1).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class SingdanceViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public SingdanceViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(2).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(2).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class MobilegameViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public MobilegameViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(3).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(3).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public SingleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(4).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(4).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class OnlineViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public OnlineViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(5).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(5).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class EsportsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public EsportsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(6).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(6).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class OtakuViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public OtakuViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(7).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(7).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon_head)
        ImageView ivIconHead;
        @InjectView(R.id.tv_online_count)
        TextView tvOnlineCount;
        @InjectView(R.id.gv_drawing)
        GridView gvDrawing;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.tv_refresh_count)
        TextView tvRefreshCount;
        @InjectView(R.id.ll_refresh)
        LinearLayout llRefresh;
        @InjectView(R.id.tv_area_name)
        TextView tv_area_name;

        public MovieViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            BiliLiveBean.DataBean.PartitionsBean.PartitionBean bean = data.getPartitions().get(8).getPartition();

            if (bean != null) {
                tv_area_name.setText(bean.getName());

                Glide.with(context)
                        .load(bean.getSub_icon().getSrc())
                        .into(ivIconHead);

                tvOnlineCount.setText(bean.getCount() + "");

                tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看更多", Toast.LENGTH_SHORT).show();
                    }
                });

                llRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 设置GridView数据
                 */
                List<BiliLiveBean.DataBean.PartitionsBean.LivesBean> lives = data.getPartitions().get(8).getLives();
                if (lives != null && lives.size() > 0) {

                    GridViewAdapter adapter = new GridViewAdapter(context, lives);
                    gvDrawing.setAdapter(adapter);
                }
            }
        }
    }


}
