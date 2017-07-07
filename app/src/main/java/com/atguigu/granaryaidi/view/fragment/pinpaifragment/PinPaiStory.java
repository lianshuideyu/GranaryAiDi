package com.atguigu.granaryaidi.view.fragment.pinpaifragment;

import android.text.TextUtils;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PinPaiStory extends BaseFragment {

    @InjectView(R.id.tv_pinpai_story)
    TextView tvPinpaiStory;

    private String story;

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;


    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_pinpai_story;
    }

    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(story)) {
            //设置品牌故事信息
            tvPinpaiStory.setText(story);

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
