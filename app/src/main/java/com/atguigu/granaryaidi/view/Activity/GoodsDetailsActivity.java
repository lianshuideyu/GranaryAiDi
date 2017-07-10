package com.atguigu.granaryaidi.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.GoodsDetailsBean;
import com.atguigu.granaryaidi.bean.ShopPinPaiBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.DensityUtil;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsDetailsActivity extends BaseActivity {

    @InjectView(R.id.goods_banner)
    Banner goodsBanner;
    @InjectView(R.id.iv_discount)
    ImageView ivDiscount;
    @InjectView(R.id.tv_brand)
    TextView tvBrand;
    @InjectView(R.id.tv_goodsname)
    TextView tvGoodsname;
    @InjectView(R.id.tv_promotion_note)
    TextView tvPromotionNote;
    @InjectView(R.id.tv_collect)
    TextView tvCollect;
    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_price_old)
    TextView tvPriceOld;
    @InjectView(R.id.rl_priceold)
    RelativeLayout rlPriceold;
    @InjectView(R.id.ll_choose_colorsize)
    LinearLayout llChooseColorsize;
    @InjectView(R.id.iv_brand_icon)
    ImageView ivBrandIcon;
    @InjectView(R.id.tv_brand_name)
    TextView tvBrandName;
    @InjectView(R.id.ll_brand)
    LinearLayout llBrand;
    @InjectView(R.id.rb_good_detail)
    RadioButton rbGoodDetail;
    @InjectView(R.id.rb_good_note)
    RadioButton rbGoodNote;
    @InjectView(R.id.rg_good_detailnote)
    RadioGroup rgGoodDetailnote;
    @InjectView(R.id.tv_good_note)
    TextView tvGoodNote;
    @InjectView(R.id.bt_good_note)
    Button btGoodNote;
    @InjectView(R.id.ll_good_note)
    LinearLayout llGoodNote;
    @InjectView(R.id.ll_good_detail)
    LinearLayout llGoodDetail;
    @InjectView(R.id.fl_details_note)
    FrameLayout flDetailsNote;
    @InjectView(R.id.ib_top_back)
    ImageButton ibTopBack;
    @InjectView(R.id.ib_top_cart)
    ImageButton ibTopCart;
    @InjectView(R.id.tv_callservice)
    ImageView tvCallservice;
    @InjectView(R.id.bt_incart)
    Button btIncart;
    @InjectView(R.id.bt_gobuy)
    Button btGobuy;
    @InjectView(R.id.activity_goods_details)
    FrameLayout activityGoodsDetails;
    /**
     * 商品对应的  id
     */
    private String goods_id;

    private static final int DETAIL_TITLE = 2;//详情类型为标题文字
    private static final int DETAIL_IMAGE = 1;//详情类型为图片
    private static final int DETAIL_CONTENT = 0;//详情类型为普通文字
    /**
     * 联网获取的数据
     */
    private GoodsDetailsBean.DataBean.ItemsBean items;

    @Override
    public void initListener() {

        /**
         * 这是RadiaGroup的监听
         */
        rgGoodDetailnote.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                switch (checkid) {
                    case R.id.rb_good_detail:
                        llGoodNote.setVisibility(View.GONE);
                        llGoodDetail.setVisibility(View.VISIBLE);

                        /**
                         * 商品详情的数据
                         */

                        //initGoodDetail();

                        break;
                    case R.id.rb_good_note:
                        llGoodDetail.setVisibility(View.GONE);
                        llGoodNote.setVisibility(View.VISIBLE);

                        //设置商品须知的
                        if (items != null) {

                            tvGoodNote.setText(items.getGood_guide().getContent());

                        }

                        break;
                }


            }
        });

        rgGoodDetailnote.check(R.id.rb_good_detail);
    }

    private void initGoodDetail() {
        if (items != null) {
            List<GoodsDetailsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();

            if(items.getGoods_desc().length() > 1) {
                //表示该商品Goods_desc有数据

                TextView textView0 = new TextView(GoodsDetailsActivity.this);
                textView0.setTextColor(Color.GRAY);
                textView0.setTextSize(DensityUtil.dip2px(GoodsDetailsActivity.this,6));
                textView0.setText(items.getGoods_desc());
//                                textView0.setPadding(0,DensityUtil.dip2px(GoodsDetailsActivity.this,10),0,DensityUtil.dip2px(GoodsDetailsActivity.this,10));
                llGoodDetail.addView(textView0);
            }

            for (int i = 0; i < goods_info.size(); i++) {
                //循环遍历，有什么控件创建什么
                int type = goods_info.get(i).getType();


                switch (type) {
                    case DETAIL_TITLE :
                        TextView textView = new TextView(GoodsDetailsActivity.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(0, DensityUtil.dip2px(GoodsDetailsActivity.this,10), 0, DensityUtil.dip2px(GoodsDetailsActivity.this,10));
                        textView.setLayoutParams(lp);

                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(DensityUtil.dip2px(GoodsDetailsActivity.this,7));
                        textView.setText(goods_info.get(i).getContent().getText());
//                                        textView.setPadding(0,DensityUtil.dip2px(GoodsDetailsActivity.this,10),0,DensityUtil.dip2px(GoodsDetailsActivity.this,10));
                        llGoodDetail.addView(textView);

                        break;
                    case DETAIL_CONTENT :
                        TextView textView2 = new TextView(GoodsDetailsActivity.this);

                        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp2.setMargins(0, DensityUtil.dip2px(GoodsDetailsActivity.this,10), 0, DensityUtil.dip2px(GoodsDetailsActivity.this,10));
                        textView2.setLayoutParams(lp2);

                        textView2.setTextColor(Color.GRAY);
                        textView2.setTextSize(DensityUtil.dip2px(GoodsDetailsActivity.this,6));
                        textView2.setText(goods_info.get(i).getContent().getText());
//                                        textView2.setPadding(0,DensityUtil.dip2px(GoodsDetailsActivity.this,10),0,DensityUtil.dip2px(GoodsDetailsActivity.this,10));
                        llGoodDetail.addView(textView2);

                        break;
                    case DETAIL_IMAGE :
                        ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(GoodsDetailsActivity.this)
                                .load(goods_info.get(i).getContent().getImg())
                                .into(imageView);

                        llGoodDetail.addView(imageView);
                        break;
                }

            }

        }
    }

    @Override
    public void initData() {

        /**
         * 购物须知 和 折扣价格 默认是 gone 的
         */


    }

    @Override
    public void initView() {

        goods_id = getIntent().getStringExtra("goods_id");
        //联网
        getDataNet();
    }

    /**
     * 联网
     */
    private void getDataNet() {
        if (TextUtils.isEmpty(goods_id)) {
            new NullPointerException("产品id为空");
            return;
        }
        String url = NetLink.GOODS_DETAILS_START + goods_id + NetLink.GOODS_DETAILS_END;
        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("gooddetail", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("gooddetail", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        GoodsDetailsBean bean = new Gson().fromJson(content, GoodsDetailsBean.class);
        items = bean.getData().getItems();

//        Log.e("gooddetail", "解析成功==" + items.getGoods_name());

        if (items != null) {

            /**
             * 设置数据
             */
            //添加banner数据
            //设置图片加载器
            goodsBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            List<String> images_item = items.getImages_item();
            if (images_item != null && images_item.size() > 0) {
                goodsBanner.setImages(images_item);
                //设置是否轮播
                goodsBanner.isAutoPlay(false);
                //设置轮播时间
//                goodsBanner.setDelayTime(1500);
                //banner设置方法全部调用完毕时最后调用
                goodsBanner.start();

            }

            tvBrand.setText(items.getOwner_name());//品牌名
            tvGoodsname.setText(items.getGoods_name());
            tvPromotionNote.setText(items.getPromotion_note());
            tvCollect.setText(items.getLike_count());

            if (TextUtils.isEmpty(items.getDiscount_price())) {//如果折扣价为空
                tvPrice.setText("￥" + items.getPrice());
            } else {
                //显示折扣前的价格
                rlPriceold.setVisibility(View.VISIBLE);
                tvPrice.setText("￥" + items.getDiscount_price());
                tvPriceOld.setText("￥" + items.getPrice());
                Glide.with(GoodsDetailsActivity.this).load(items.getPromotion_imgurl()).into(ivDiscount);
            }
            //设置品牌的图标
            Glide.with(GoodsDetailsActivity.this).load(items.getBrand_info().getBrand_logo()).into(ivBrandIcon);
            tvBrandName.setText(items.getBrand_info().getBrand_name());

            /**
             * 一进来页面就加载详情内容
             */
            initGoodDetail();
        }else {
            //没有数据
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast("商品已下架");
                }
            });
            //品牌栏不能点击
            llBrand.setEnabled(false);
            llChooseColorsize.setEnabled(false);
            tvCallservice.setEnabled(false);
            btIncart.setEnabled(false);
            btGobuy.setEnabled(false);
            tvCollect.setEnabled(false);
            ivShare.setEnabled(false);
            ibTopCart.setEnabled(false);
        }

    }

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_details;
    }


    @OnClick({R.id.goods_banner, R.id.tv_collect, R.id.iv_share, R.id.ll_choose_colorsize, R.id.ll_brand, R.id.bt_good_note, R.id.ib_top_back, R.id.ib_top_cart, R.id.tv_callservice, R.id.bt_incart, R.id.bt_gobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods_banner:
                break;
            case R.id.tv_collect:
                showToast("收藏");

                break;
            case R.id.iv_share:
                showToast("分享");
                break;
            case R.id.ll_choose_colorsize:
                showToast("选择尺寸颜色数量");
                break;
            case R.id.ll_brand:
//                showToast("进入品牌介绍");
                //将bean对象传过去
                Intent intent = new Intent(GoodsDetailsActivity.this, ShopPinpaiActivity.class);
                ShopPinPaiBean.DataBean.ItemsBean bean = new ShopPinPaiBean.DataBean.ItemsBean();
                bean.setBrand_id(Integer.parseInt(items.getBrand_info().getBrand_id()));
                bean.setBrand_name(items.getBrand_info().getBrand_name());
                bean.setBrand_logo(items.getBrand_info().getBrand_logo());

                intent.putExtra(NetLink.SHOP_PINPAI_LIST,bean);
                startActivity(intent);

                break;
            case R.id.bt_good_note:
                showToast("售后须知");
                break;
            case R.id.ib_top_back:
                finish();
                break;
            case R.id.ib_top_cart:
                showToast("购物车");
                break;
            case R.id.tv_callservice:
                showToast("客服");
                break;
            case R.id.bt_incart:
                showToast("加入购物车");
                break;
            case R.id.bt_gobuy:
                showToast("直接购买");
                break;
        }
    }
}
