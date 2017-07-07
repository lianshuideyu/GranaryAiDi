package com.atguigu.granaryaidi.view.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenDefaultBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenFansfragment;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenGuanzhufragment;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenLikefragment;
import com.atguigu.granaryaidi.view.fragment.darenfragment.DarenRecommendfragment;
import com.bumptech.glide.Glide;

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
    //从上级页面传来的数据
    private DaRenDefaultBean.DataBean.ItemsBean bean;


    private List<BaseFragment> fragments;
    //切换的位置
    private int position;

    private Fragment tempFragment;//用于缓存的Fragment

    private DarenLikefragment darenlike;
    private DarenRecommendfragment darenrecomm;
    private DarenGuanzhufragment darenguanzhu;
    private DarenFansfragment darenfans;

    @Override
    public void initListener() {

        rgDaren.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Log.e("TAG","checkid==" + checkid);
                //方法一
                switch (checkid) {
                    case R.id.rb_daren_like :
                        position = 0;

                        break;
                    case R.id.rb_daren_recommend :
                        position = 1;

                        break;
                    case R.id.rb_daren_care :
                        position = 2;

                        break;
                    case R.id.rb_daren_fans :
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

        if(bean != null) {

            //设置品牌头像信息 和 品牌名称
            Glide.with(this).load(bean.getUser_images().getOrig())
                    .error(R.drawable.brand_logo_empty).placeholder(R.drawable.brand_logo_empty)
                    .into(ivDarenIcon);
            tvName.setText(bean.getUsername());
            tvProfession.setText(bean.getDuty());

            /**
             * 联网请求数据 传给 相应的 fragment
             */
            getDataFromNet();
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        darenlike = new DarenLikefragment();
        darenrecomm = new DarenRecommendfragment();
        darenguanzhu = new DarenGuanzhufragment();
        darenfans = new DarenFansfragment();

        fragments.add(darenlike);
        fragments.add(darenrecomm);
        fragments.add(darenguanzhu);
        fragments.add(darenfans);
    }

    /**
     * 联网
     */
    private void getDataFromNet() {
        //这里需要拼接联网 链接
//        String url = NetLink.SHOP_PINPAI_LISTSTART + bean.getBrand_id() + NetLink.SHOP_PINPAI_LISTEND;
//
//        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
//            @Override
//            public void onSuccess(String content) {
//                Log.e("pinpai","联网成功==" + content);
//                if(!TextUtils.isEmpty(content)) {
//                    //解析数据
//                    processData(content);
//                }
//
//            }
//
//            @Override
//            public void onFailure(String content) {
//                Log.e("pinpai","联网失败==" + content);
//            }
//        });


    }

    /**
     * 解析数据
     */
    private void processData(String content) {
//        ShopPinPaiListBean bean = new Gson().fromJson(content, ShopPinPaiListBean.class);
//        items = bean.getData().getItems();
////
////        Log.e("pinpai","二级页面解析成功==" + items.get(0).getGoods_name());
////
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
    @Override
    public void initView() {

        /**
         * 获取 前一页面传来的数据
         */
        bean = (DaRenDefaultBean.DataBean.ItemsBean) getIntent()
                .getSerializableExtra(NetLink.DAREN_DETAILS);
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
     * @param currentFragment
     */
    private void selectFragment(BaseFragment currentFragment) {
        if(currentFragment == null) {
            return;
        }

        //开启事务管理
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(currentFragment != tempFragment) {
            //第一次进来temp为空，肯定不等于

            //先隐藏之前的
            if(tempFragment != null) {
                ft.hide(tempFragment);
            }


            if(!currentFragment.isAdded()) {
                //判断是否添加过，没有就添加
                ft.add(R.id.fl_daren_details,currentFragment);

            }else {
                //添加过就显示缓存的
                ft.show(currentFragment);
            }

            tempFragment = currentFragment;
            ft.commit();//提交事务，不要忘记
        }
    }
}
