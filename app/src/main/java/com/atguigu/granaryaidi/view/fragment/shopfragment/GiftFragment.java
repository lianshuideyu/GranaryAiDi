package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.view.Activity.ShopTypeActivity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/6.
 */

public class GiftFragment extends BaseFragment {
    @InjectView(R.id.iv_gift_top)
    ImageView ivGiftTop;
    @InjectView(R.id.iv_gift_festival)
    ImageView ivGiftFestival;
    @InjectView(R.id.iv_gift_love)
    ImageView ivGiftLove;
    @InjectView(R.id.iv_gift_brithday)
    ImageView ivGiftBrithday;
    @InjectView(R.id.iv_gift_fridends)
    ImageView ivGiftFridends;
    @InjectView(R.id.iv_gift_child)
    ImageView ivGiftChild;
    @InjectView(R.id.iv_gift_parent)
    ImageView ivGiftParent;
    @InjectView(R.id.ll_setting_gift)
    LinearLayout llSettingGift;

    /**
     * 链接的数组
     * @return
     */
    private String[] giftUrls = new String[]{

            NetLink.SHOP_GIFT_TOP,NetLink.SHOP_GIFT_FESTIVAL,
            NetLink.SHOP_GIFT_LOVE,NetLink.SHOP_GIFT_BRITHDAY,
            NetLink.SHOP_GIFT_FRIENDS,NetLink.SHOP_GIFT_CHILD,
            NetLink.SHOP_GIFT_PARENT
    };

    @Override
    public int getLayoutId() {
        return R.layout.giftfragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.iv_gift_top, R.id.iv_gift_festival, R.id.iv_gift_love, R.id.iv_gift_brithday, R.id.iv_gift_fridends, R.id.iv_gift_child, R.id.iv_gift_parent, R.id.ll_setting_gift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_gift_top:
                //顶部大图点击事件

                startShopTypeActivity(giftUrls[0]);

                break;
            case R.id.iv_gift_festival:
                //节日点击事件
                startShopTypeActivity(giftUrls[1]);
                break;
            case R.id.iv_gift_love:
                //爱情点击事件
                startShopTypeActivity(giftUrls[2]);
                break;
            case R.id.iv_gift_brithday:
                //生日点击事件
                startShopTypeActivity(giftUrls[3]);
                break;
            case R.id.iv_gift_fridends:
                //朋友点击事件
                startShopTypeActivity(giftUrls[4]);
                break;
            case R.id.iv_gift_child:
                //孩子点击事件
                startShopTypeActivity(giftUrls[5]);
                break;
            case R.id.iv_gift_parent:
                //父母点击事件
                startShopTypeActivity(giftUrls[6]);
                break;
            case R.id.ll_setting_gift:
                //设置礼物提醒

                break;
        }
    }

    /**
     * 打开商品列表的 Activity
     * @param giftUrl
     */
    private void startShopTypeActivity(String giftUrl) {
        Intent intent = new Intent(context, ShopTypeActivity.class);
        //将对应的类型链接传到 商品列表页面
        intent.putExtra(NetLink.SHOP_URL,giftUrl);
        startActivity(intent);
    }
}
