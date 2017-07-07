package com.atguigu.granaryaidi.view.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopPinPaiBean;
import com.atguigu.granaryaidi.bean.ShopPinPaiListBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.fragment.pinpaifragment.PinPaiProduct;
import com.atguigu.granaryaidi.view.fragment.pinpaifragment.PinPaiStory;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class ShopPinpaiActivity extends BaseActivity {


    @InjectView(R.id.iv_pinpai_icon)
    ImageView ivPinpaiIcon;
    @InjectView(R.id.tv_pinpai_name)
    TextView tvPinpaiName;
    @InjectView(R.id.rg_pinpai)
    RadioGroup rgPinpai;
    @InjectView(R.id.fl_pinpai)
    FrameLayout flPinpai;

    private List<BaseFragment> fragments;
    //切换的位置
    private int position;

    private Fragment tempFragment;//用于缓存的Fragment
    /**
     * 获取 前一页面传来的数据
     */
    private ShopPinPaiBean.DataBean.ItemsBean bean;
    /**
     * 联网获取的数据
     */
    private List<ShopPinPaiListBean.DataBean.ItemsBean> items;

    private PinPaiStory pinPaiStory;
    private PinPaiProduct pinPaiProduct;

    @Override
    public void initListener() {

        rgPinpai.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Log.e("TAG","checkid==" + checkid);
                //方法一
                switch (checkid) {
                    case R.id.rb_pinpai_story :
                        position = 0;
                        //将品牌故事传给PinPaiStory（）

                        break;
                    case R.id.rb_pinpai_product :
                        position = 1;

                        break;
                }
//
                BaseFragment currentFragment = fragments.get(position);
                selectFragment(currentFragment);

            }
        });
//
//        //默认选择 品牌产品
        rgPinpai.check(R.id.rb_pinpai_product);
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();

        pinPaiStory = new PinPaiStory();
        pinPaiProduct = new PinPaiProduct();

        fragments.add(pinPaiStory);
        fragments.add(pinPaiProduct);

        if(bean != null) {

            //设置品牌头像信息 和 品牌名称
            Glide.with(this).load(bean.getBrand_logo())
                    .error(R.drawable.brand_logo_empty).placeholder(R.drawable.brand_logo_empty)
                    .into(ivPinpaiIcon);
            tvPinpaiName.setText(bean.getBrand_name());

            /**
             * 联网请求数据 传给 相应的 fragment
             */
            getDataFromNet();
        }
    }

    /**
     * 联网
     */
    private void getDataFromNet() {
        //这里需要拼接联网 链接
        String url = NetLink.SHOP_PINPAI_LISTSTART + bean.getBrand_id() + NetLink.SHOP_PINPAI_LISTEND;

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("pinpai","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("pinpai","联网失败==" + content);
            }
        });


    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopPinPaiListBean bean = new Gson().fromJson(content, ShopPinPaiListBean.class);
        items = bean.getData().getItems();
//
//        Log.e("pinpai","二级页面解析成功==" + items.get(0).getGoods_name());
//
        if(items != null && items.size() > 0) {
            //将相应的数据传递给fragment
            //将品牌故事传给 PinPaiStory
            pinPaiStory.setStory(items.get(0).getBrand_info().getBrand_desc());

            //将产品列表的对象传给 PinPaiProduct
            pinPaiProduct.setBean(bean);

        }

    }

    @Override
    public void initView() {
        /**
         * 获取 前一页面传来的数据
         */
        bean = (ShopPinPaiBean.DataBean.ItemsBean) getIntent()
                .getSerializableExtra(NetLink.SHOP_PINPAI_LIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_pinpai;
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
                ft.add(R.id.fl_pinpai,currentFragment);

            }else {
                //添加过就显示缓存的
                ft.show(currentFragment);
            }

            tempFragment = currentFragment;
            ft.commit();//提交事务，不要忘记
        }
    }
}
