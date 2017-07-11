package com.atguigu.granaryaidi.cart;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShopCartActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.ib_shop_menu)
    ImageButton ibShopMenu;
    @InjectView(R.id.lv_cart)
    ListView lvCart;
    @InjectView(R.id.tv_manjian)
    TextView tvManjian;
    @InjectView(R.id.tv_zhekou)
    TextView tvZhekou;
    @InjectView(R.id.tv_baozhuang)
    TextView tvBaozhuang;
    @InjectView(R.id.tv_baoyou)
    TextView tvBaoyou;
    @InjectView(R.id.cb_all_ischeck)
    CheckBox cbAllIscheck;
    @InjectView(R.id.tv_price_all)
    TextView tvPriceAll;
    @InjectView(R.id.bt_tobuy)
    Button btTobuy;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        tvTitle.setText("购物车");
        ibShopBack.setVisibility(View.VISIBLE);
        ibShopMenu.setVisibility(View.VISIBLE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_cart;
    }


    @OnClick({R.id.ib_shop_back, R.id.ib_shop_menu, R.id.bt_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                finish();

                break;
            case R.id.ib_shop_menu:
                showToast("编辑购物车");

                break;
            case R.id.bt_tobuy:
                showToast("去结算");

                break;
        }
    }
}
