package com.atguigu.granaryaidi.view.Activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenLikeBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenGuanzhufragment;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenLikefragment;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenRecommendfragment;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class DarenDetailsActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_profession)
    TextView tvProfession;
    @InjectView(R.id.bt_guanzhu)
    Button btGuanzhu;
    @InjectView(R.id.bt_sixin)
    Button btSixin;
    @InjectView(R.id.iv_daren_icon)
    ImageView ivDarenIcon;
    @InjectView(R.id.rg_daren)
    RadioGroup rgDaren;

    @InjectView(R.id.rb_daren_like)
    public RadioButton rbDarenLike;
    @InjectView(R.id.rb_daren_recommend)
    public RadioButton rbDarenRecommend;
    @InjectView(R.id.rb_daren_care)
    public RadioButton rbDarenCare;
    @InjectView(R.id.rb_daren_fans)
    public RadioButton rbDarenFans;

    //从上级页面传来的数据
//    private DaRenDefaultBean.DataBean.ItemsBean bean;


    private List<BaseFragment> fragments;
    //切换的位置
    private int position;

    private Fragment tempFragment;//用于缓存的Fragment

    private DarenLikefragment darenlike;
    private DarenRecommendfragment darenrecomm;
    private DarenGuanzhufragment darenguanzhu;
    private DarenGuanzhufragment darenfans;

    //从上级页面传来的数据
    private String uid;
    private String username;
    private String duty;
    private String orig;

    private static DarenDetailsActivity instance;

    @Override
    public void initListener() {

        rgDaren.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Log.e("TAG", "checkid==" + checkid);
                //方法一
                switch (checkid) {
                    case R.id.rb_daren_like:
                        position = 0;

                        break;
                    case R.id.rb_daren_recommend:
                        position = 1;

                        break;
                    case R.id.rb_daren_care:
                        position = 2;

                        break;
                    case R.id.rb_daren_fans:
                        position = 3;

                        break;
                }
//
                BaseFragment currentFragment = fragments.get(position);
                selectFragment(currentFragment);

            }
        });
//
//        //默认选择 品牌产品
        rgDaren.check(R.id.rb_daren_recommend);
    }

    @Override
    public void initData() {

        initFragment();
        /**
         * 联网,这里是为了获取 喜欢，推荐，关注，粉丝的数量
         */
        getDataFromNet();

//        if(bean != null) {
//
//            //设置品牌头像信息 和 品牌名称
//            Glide.with(this).load(bean.getUser_images().getOrig())
//                    .error(R.drawable.brand_logo_empty).placeholder(R.drawable.brand_logo_empty)
//                    .into(ivDarenIcon);
//            tvName.setText(bean.getUsername());
//            tvProfession.setText(bean.getDuty());
//            ibShopBack.setVisibility(View.VISIBLE);
//            tvTitle.setText(bean.getUsername());
//
//            /*把数据传给各个fragment页面,注意：：切换到fragment后
//            在fragment的onResume（）方法中再执行联网设置数据等操作，
//            不会容易出现fragment中的控件空指针,因为布局还没加载出来,控件还未初始化*/
//
//            //这里需要拼接联网 链接
//            String url = NetLink.DAREN_DETAILS_RECOMMEND_START + bean.getUid() + NetLink.DAREN_DETAILS_RECOMMEND_END;
//            darenrecomm.setUrl(url);
//
//            String url1 = NetLink.DAREN_DETAILS_GUANZHU_START + bean.getUid() + NetLink.DAREN_DETAILS_GUANZHU_END;
//            darenguanzhu.setUrl(url1);
//            /**
//             * 联网请求数据 传给 相应的 fragment
//             */
//            //getDataFromNet();
//        }

        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(orig)) {
            //设置品牌头像信息 和 品牌名称
            Glide.with(this).load(orig).error(R.drawable.brand_logo_empty)
                    .placeholder(R.drawable.brand_logo_empty).into(ivDarenIcon);

            tvName.setText(username);
            tvProfession.setText(duty);
            ibShopBack.setVisibility(View.VISIBLE);
            tvTitle.setText(username);


            //这里需要拼接联网 链接
            String url = NetLink.DAREN_DETAILS_RECOMMEND_START + uid + NetLink.DAREN_DETAILS_RECOMMEND_END;
            darenrecomm.setUrl(url);

            String url1 = NetLink.DAREN_DETAILS_GUANZHU_START + uid + NetLink.DAREN_DETAILS_GUANZHU_END;
            darenguanzhu.setUrl(url1, 2);//第二个参数为位置

            String url2 = NetLink.DAREN_DETAILS_FANS_START + uid + NetLink.DAREN_DETAILS_FANS_END;
            darenfans.setUrl(url2, 3);

            String url3 = NetLink.DAREN_DETAILS_LIKE_START + uid + NetLink.DAREN_DETAILS_LIKE_END;
            darenlike.setUrl(url3);
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        darenlike = new DarenLikefragment();
        darenrecomm = new DarenRecommendfragment();
        darenguanzhu = new DarenGuanzhufragment();
        //关注的fragment和粉丝的fragment是一样的！！!
//        darenfans = new DarenFansfragment();
        darenfans = new DarenGuanzhufragment();

        fragments.add(darenlike);
        fragments.add(darenrecomm);
        fragments.add(darenguanzhu);
        fragments.add(darenfans);
    }

    /**
     * 联网,这里是为了获取 喜欢，推荐，关注，粉丝的数量
     */
    private void getDataFromNet() {
        //这里需要拼接联网 链接
        if (!TextUtils.isEmpty(uid)) {

            String url = NetLink.DAREN_DETAILS_LIKE_START + uid + NetLink.DAREN_DETAILS_LIKE_END;

            HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
                @Override
                public void onSuccess(String content) {
                    Log.e("daren", "联网成功==" + content);
                    if (!TextUtils.isEmpty(content)) {
                        //解析数据
                        processData(content);
                    }

                }

                @Override
                public void onFailure(String content) {
                    Log.e("daren", "联网失败==" + content);
                }
            });

        }
    }

    /**
     * 解析数据,设置 喜欢，推荐，关注，粉丝的数量
     */
    private void processData(String content) {
        DaRenLikeBean bean = new Gson().fromJson(content, DaRenLikeBean.class);

//        Log.e("darenlike", "二级页面解析成功==" + bean.getData().getItems().getUser_name());
        DaRenLikeBean.DataBean.ItemsBean items = bean.getData().getItems();
        if(items != null) {
            rbDarenLike.setText("喜欢\n" + items.getLike_count());
            rbDarenRecommend.setText("推荐\n" + items.getRecommendation_count());
            rbDarenCare.setText("关注\n" + items.getFollowing_count());
            rbDarenFans.setText("粉丝\n" + items.getFollowed_count());

        }


//        if(items != null && items.size() > 0) {
//            //将相应的数据传递给fragment
//            //将品牌故事传给 PinPaiStory
//            pinPaiStory.setStory(items.get(0).getBrand_info().getBrand_desc());
//
//            //将产品列表的对象传给 PinPaiProduct
//            pinPaiProduct.setBean(bean);
//
//        }

    }

    public static Activity getInstance() {
        return instance;
    }

    @Override
    public void initView() {

        this.instance = DarenDetailsActivity.this;
        /**
         * 获取 前一页面传来的数据
         */
//        bean = (DaRenDefaultBean.DataBean.ItemsBean) getIntent()
//                .getSerializableExtra(NetLink.DAREN_DETAILS);

        uid = getIntent().getStringExtra("uid");
        username = getIntent().getStringExtra("username");
        duty = getIntent().getStringExtra("duty");
        orig = getIntent().getStringExtra("orig");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_daren_details;
    }


    @OnClick({R.id.ib_shop_back, R.id.bt_guanzhu, R.id.bt_sixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                finish();

                break;
            case R.id.bt_guanzhu:
                showToast("关注");

                break;
            case R.id.bt_sixin:
                showToast("私信");

                break;
        }
    }

    /**
     * 切换Fragment
     *
     * @param currentFragment
     */
    private void selectFragment(BaseFragment currentFragment) {
        if (currentFragment == null) {
            return;
        }

        //开启事务管理
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currentFragment != tempFragment) {
            //第一次进来temp为空，肯定不等于

            //先隐藏之前的
            if (tempFragment != null) {
                ft.hide(tempFragment);
            }


            if (!currentFragment.isAdded()) {
                //判断是否添加过，没有就添加
                ft.add(R.id.fl_daren_details, currentFragment);

            } else {
                //添加过就显示缓存的
                ft.show(currentFragment);
            }

            tempFragment = currentFragment;
            ft.commit();//提交事务，不要忘记
        }
    }

}
