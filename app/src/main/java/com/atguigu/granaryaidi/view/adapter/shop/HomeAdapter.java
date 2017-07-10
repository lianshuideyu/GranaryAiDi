package com.atguigu.granaryaidi.view.adapter.shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.Shoptwo.ShopTwoHomeBean;
import com.atguigu.granaryaidi.view.Activity.ShopWebviewActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.BaseViewHolder> {

    private final List<ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX> datas;
    private final Context context;


    public HomeAdapter(Context context, List<ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX> lists) {
        this.context = context;
        this.datas = lists;
    }


    /**
     * 视频
     */
    private static final int TYPE_ONE = 0;

    /**
     * 图片
     */
    private static final int TYPE_TWO = 1;

    /**
     * 文字
     */
    private static final int TYPE_FOUR = 2;
    /**
     * 购物接口
     */
    private static final int TYPE_shop = 3;

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean = datas.get(position);
        //根据位置，从列表中得到一个数据对象
        int type = bean.getHome_type();
        if (type == 1) {
            itemViewType = TYPE_ONE;
        } else if (type == 2) {
            itemViewType = TYPE_TWO;
        } else if (type == 4) {
            itemViewType = TYPE_FOUR;
        } else if (type == 6) {
            itemViewType = TYPE_shop;
        }

//        Log.e("home", "homeadapter__type==" + itemViewType);
        return itemViewType;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case TYPE_ONE:
                baseViewHolder = new OneViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.one_home_item, parent, false));
                Log.e("home", "onCreateViewHolder---TYPE_ONE");

                break;
            case TYPE_TWO:
                baseViewHolder = new TwoViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.two_home_item, parent, false));
                Log.e("home", "onCreateViewHolder---TYPE_TWO");
                break;
            case TYPE_FOUR:
                baseViewHolder = new FourViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.four_home_item, parent, false));
                Log.e("home", "onCreateViewHolder---TYPE_FOUR");
                break;
            case TYPE_shop:
                baseViewHolder = new ShopViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.shop_home_item, parent, false));
                Log.e("home", "onCreateViewHolder---TYPE_shop");
                break;

        }

        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_ONE:
                OneViewHolder oneHoder = (OneViewHolder) holder;

                oneHoder.setData(datas.get(position));

                break;

            case TYPE_TWO:
                TwoViewHolder twoHoder = (TwoViewHolder) holder;

                twoHoder.setData(datas.get(position));

                break;

            case TYPE_FOUR:
                FourViewHolder fourHoder = (FourViewHolder) holder;

                fourHoder.setData(datas.get(position));

                break;
            case TYPE_shop:
                ShopViewHolder shopHoder = (ShopViewHolder) holder;

                shopHoder.setData(datas.get(position));

                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ShopViewHolder extends BaseViewHolder {

        private ImageView iv_home_shop;

        public ShopViewHolder(View itemView) {
            super(itemView);

            iv_home_shop = (ImageView) itemView.findViewById(R.id.iv_home_shop);
        }

        @Override
        public void setData(ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean) {
            super.setData(bean);

            //加载图片
            String img = bean.getList().get(0).getPic_url();
            Glide.with(context)
                    .load(img)
                    .error(R.drawable.brand_bg)
                    .placeholder(R.drawable.brand_bg)
                    .into(this.iv_home_shop);
        }
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);

        }

        /**
         * 设置公共的数据
         */
        public void setData(ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean) {

        }
    }

    /**
     * 只有一张图片的 item
     */
    class OneViewHolder extends BaseViewHolder {

        private ImageView ivHomeOne;

        public OneViewHolder(View itemView) {
            super(itemView);

            ivHomeOne = (ImageView) itemView.findViewById(R.id.iv_home_one);
        }

        @Override
        public void setData(final ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean) {
            super.setData(bean);

            //加载图片
            String img = bean.getOne().getPic_url();
            Glide.with(context)
                    .load(img)
                    .error(R.drawable.brand_bg)
                    .placeholder(R.drawable.brand_bg)
                    .into(this.ivHomeOne);

            ivHomeOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getOne());
                    intent.putExtra("htmlurl", bean.getOne().getTopic_url());
                    intent.putExtra("htmlname", bean.getOne().getTopic_name());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * 只有二张图片的 item
     */
    class TwoViewHolder extends BaseViewHolder {

        private ImageView ivHomeTwoa;
        private ImageView ivHomeTwob;

        public TwoViewHolder(View itemView) {
            super(itemView);

            ivHomeTwoa = (ImageView) itemView.findViewById(R.id.iv_home_twoa);
            ivHomeTwob = (ImageView) itemView.findViewById(R.id.iv_home_twob);
        }

        @Override
        public void setData(final ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean) {
            super.setData(bean);

            //加载图片
            String img1 = bean.getOne().getPic_url();
            Glide.with(context).load(img1).error(R.drawable.brand_bg)
                    .placeholder(R.drawable.brand_bg).into(this.ivHomeTwoa);

            //加载图片
            String img2 = bean.getTwo().getPic_url();
            Glide.with(context).load(img2).error(R.drawable.brand_bg)
                    .placeholder(R.drawable.brand_bg).into(this.ivHomeTwob);

            ivHomeTwoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getOne());
                    intent.putExtra("htmlurl", bean.getOne().getTopic_url());
                    intent.putExtra("htmlname", bean.getOne().getTopic_name());
                    context.startActivity(intent);
                }
            });

            ivHomeTwob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getTwo());
                    intent.putExtra("htmlurl", bean.getTwo().getTopic_url());
                    intent.putExtra("htmlname", bean.getTwo().getTopic_name());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * 只有四张图片的 item
     */
    class FourViewHolder extends BaseViewHolder {

        private ImageView ivHomeTwoa;
        private ImageView ivHomeTwob;
        private ImageView ivHomeTwoc;
        private ImageView ivHomeTwod;

        public FourViewHolder(View itemView) {
            super(itemView);

            ivHomeTwoa = (ImageView) itemView.findViewById(R.id.iv_home_foura);
            ivHomeTwob = (ImageView) itemView.findViewById(R.id.iv_home_fourb);
            ivHomeTwoc = (ImageView) itemView.findViewById(R.id.iv_home_fourc);
            ivHomeTwod = (ImageView) itemView.findViewById(R.id.iv_home_fourd);
        }

        @Override
        public void setData(final ShopTwoHomeBean.DataBean.ItemsBean.ListBeanX bean) {
            super.setData(bean);

            //加载图片
            String img1 = bean.getOne().getPic_url();
            Glide.with(context).load(img1).error(R.drawable.bg_topic_favour)
                    .placeholder(R.drawable.bg_topic_favour).into(this.ivHomeTwoa);

            //加载图片
            String img2 = bean.getTwo().getPic_url();
            Glide.with(context).load(img2).error(R.drawable.bg_topic_favour)
                    .placeholder(R.drawable.bg_topic_favour).into(this.ivHomeTwob);

            //加载图片
            String img3 = bean.getThree().getPic_url();
            Glide.with(context).load(img3).error(R.drawable.bg_topic_favour)
                    .placeholder(R.drawable.bg_topic_favour).into(this.ivHomeTwoc);

            //加载图片
            String img4 = bean.getFour().getPic_url();
            Glide.with(context).load(img4).error(R.drawable.bg_topic_favour)
                    .placeholder(R.drawable.bg_topic_favour).into(this.ivHomeTwod);

            ivHomeTwoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getOne());
                    intent.putExtra("htmlurl", bean.getOne().getTopic_url());
                    intent.putExtra("htmlname", bean.getOne().getTopic_name());
                    context.startActivity(intent);
                }
            });

            ivHomeTwob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getTwo());
                    intent.putExtra("htmlurl", bean.getTwo().getTopic_url());
                    intent.putExtra("htmlname", bean.getTwo().getTopic_name());
                    context.startActivity(intent);
                }
            });

            ivHomeTwoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getThree());
                    intent.putExtra("htmlurl", bean.getThree().getTopic_url());
                    intent.putExtra("htmlname", bean.getThree().getTopic_name());
                    context.startActivity(intent);
                }
            });

            ivHomeTwod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ShopWebviewActivity.class);
//                    intent.putExtra("html",bean.getFour());
                    intent.putExtra("htmlurl", bean.getFour().getTopic_url());
                    intent.putExtra("htmlname", bean.getFour().getTopic_name());
                    context.startActivity(intent);
                }
            });
        }
    }
}
