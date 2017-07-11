package com.atguigu.granaryaidi.cart;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.cart.adapter.CartShopAdapter;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShopCartActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.tv_edit)
    TextView tv_edit;
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


    private List<GoodsBean> products;
    private CartShopAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        products = CartStorage.getInstance(this).getAllData();
        if(products != null) {
            //设置适配器，设置数据,将需要的控件传过去
            adapter = new CartShopAdapter(ShopCartActivity.this,products,cbAllIscheck,tvPriceAll);

            lvCart.setAdapter(adapter);
        }

    }

    @Override
    public void initView() {
        tvTitle.setText("购物车");
        ibShopBack.setVisibility(View.VISIBLE);
        tv_edit.setVisibility(View.VISIBLE);
        tv_edit.setText("编辑");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_cart;
    }


    /**
     * 购物车是否为编辑状态
     */
    private boolean iseditCart = false;
    @OnClick({R.id.ib_shop_back, R.id.tv_edit, R.id.bt_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                finish();

                break;
            case R.id.tv_edit:
//                showToast("编辑购物车");
                editCart();

                break;
            case R.id.bt_tobuy:
                showToast("去结算");

                break;
        }
    }

    /**
     * 编辑购物车
     */
    private void editCart() {
        if(!iseditCart) {
            //进入编辑状态---显示可编辑的item--刷新适配器--显示新的布局
            tv_edit.setText("完成");
            iseditCart = true;
            //将iseditCart传给适配器
            adapter.setisEdit(iseditCart);
            adapter.notifyDataSetChanged();

        }else {
            //完成编辑
            tv_edit.setText("编辑");
            iseditCart = false;
            //将iseditCart传给适配器
            adapter.setisEdit(iseditCart);
            adapter.notifyDataSetChanged();
        }
    }
}
