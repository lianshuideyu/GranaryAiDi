package com.atguigu.granaryaidi.view.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.bilibili.BiliLiveBean;
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


    private final BiliLiveBean.DataBean data;
    private final Context context;

    /**
     * 六种类型
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


    private LayoutInflater inflater;

    public BiliLiveAdapter(Context context, BiliLiveBean.DataBean data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int currentType = -1;

        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == DRAWING_AREA) {
            currentType = DRAWING_AREA;
        } else if (position == ENT_LIFE) {
            currentType = ENT_LIFE;
        } else if (position == SING_DANCE) {
            currentType = SING_DANCE;
        } else if (position == MOBILE_GAME) {
            currentType = MOBILE_GAME;
        } else if (position == SINGLE) {
            currentType = SINGLE;
        } else if (position == ONLINE) {
            currentType = ONLINE;
        } else if (position == E_SPORTS) {
            currentType = E_SPORTS;
        } else if (position == OTAKU) {
            currentType = OTAKU;
        } else if (position == MOVIE) {
            currentType = MOVIE;
        }

        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(inflater.inflate(R.layout.banner_viewpager, null));
        }
//        else if (viewType == CHANNEL) {
//            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));
//        } else if (viewType == ACT) {
//            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));
//        } else if (viewType == SECKILL) {
//            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));
//        } else if (viewType == RECOMMEND) {
//            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
//        } else if (viewType == HOT) {
//            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
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

        }
    }

    @Override
    public int getItemCount() {
        return 1;
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
            for(int i = 0; i < banner.size(); i++) {

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
}
