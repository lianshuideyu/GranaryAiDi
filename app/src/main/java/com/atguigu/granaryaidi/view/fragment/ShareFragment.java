package com.atguigu.granaryaidi.view.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ShareFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.ib_shop_search)
    ImageButton ibShopSearch;
    @InjectView(R.id.ib_shop_menu)
    ImageButton ibShopMenu;
    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("笑一笑十年少");
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_share_main;
    }

    @Override
    protected void initView() {
        ibShopMenu.setVisibility(View.VISIBLE);
        ibShopSearch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.ib_shop_search, R.id.ib_shop_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_shop_menu:
                Toast.makeText(context, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
