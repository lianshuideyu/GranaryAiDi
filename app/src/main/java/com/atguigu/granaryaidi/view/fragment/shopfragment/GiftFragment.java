package com.atguigu.granaryaidi.view.fragment.shopfragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

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
                break;
            case R.id.iv_gift_festival:
                break;
            case R.id.iv_gift_love:
                break;
            case R.id.iv_gift_brithday:
                break;
            case R.id.iv_gift_fridends:
                break;
            case R.id.iv_gift_child:
                break;
            case R.id.iv_gift_parent:
                break;
            case R.id.ll_setting_gift:
                break;
        }
    }
}
