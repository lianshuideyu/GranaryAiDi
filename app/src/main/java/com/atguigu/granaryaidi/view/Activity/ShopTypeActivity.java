package com.atguigu.granaryaidi.view.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.ShopTypeListBean;
import com.atguigu.granaryaidi.cart.ShopCartActivity;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.adapter.shop.ClassifyListAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShopTypeActivity extends BaseActivity {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shop_back)
    ImageButton ibShopBack;
    @InjectView(R.id.ib_shop_cart)
    ImageButton ibShopCart;
    @InjectView(R.id.ll_price_check)
    LinearLayout llPriceCheck;
    @InjectView(R.id.gv_shop_type)
    GridView gvShopType;

    @InjectView(R.id.tv_selector_price)
    TextView tvSelectorPrice;


    /**
     * 联网链接
     */
    private String url;
    /**
     * 联网获取的数据集合
     */
    private List<ShopTypeListBean.DataBean.ItemsBean> items;
    private ClassifyListAdapter adapter;

    /**
     * 用于缓存popuwindow中的textview
     * 以下写成全局的保证为同一个对象
     */
    private TextView temptext;
    View contentView;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    private PopupWindow popupWindow;

    /**
     * 设置转场动画
     */
    @Override
    public void activityAnmotion() {
        super.activityAnmotion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //方法二,从右边进来
            android.transition.Transition explode = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_right);
            getWindow().setEnterTransition(explode);
        }

    }

    @Override
    public void initListener() {

        gvShopType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(ShopTypeActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("goods_id",items.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

        //联网
        getDataFromNet();
    }



    @Override
    public void initView() {
        //获取传递过来的联网链接
        url = getIntent().getStringExtra(NetLink.SHOP_URL);
        tvTitle.setText("商店");

        //标题栏的按键显示
        ibShopBack.setVisibility(View.VISIBLE);
        ibShopCart.setVisibility(View.VISIBLE);

        /**
         * 初始化popuwindow 相关
         */
        contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window_shoptype, null);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_type;
    }


    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.ib_shop_back, R.id.ll_price_check,R.id.ib_shop_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_back:
                //点击返回
                finish();

                break;
            case R.id.ll_price_check:
                //点击价格筛选--此处应该是popuwindow
//                showToast("价格筛选");

                showPopupWindow(view);
                break;
            case R.id.ib_shop_cart:
//                showToast("购物车");
                Intent intent3 = new Intent(ShopTypeActivity.this, ShopCartActivity.class);
                startActivity(intent3);
                break;

        }
    }

    /**
     * 联网
     */
    private void getDataFromNet() {
        if(TextUtils.isEmpty(url)) {
            new NullPointerException("联网链接为空");
            return;
        }

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("shoptype","联网成功==" + content);
                if(!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }

            }

            @Override
            public void onFailure(String content) {
                Log.e("shoptype","联网失败==" + content);
            }
        });
    }
    /**
     * 解析数据
     */
    private void processData(String content) {
        ShopTypeListBean bean = new Gson().fromJson(content, ShopTypeListBean.class);
        items = bean.getData().getItems();
//
        Log.e("shoptype","解析==" + items.get(0).getGoods_name());

        if(items != null && items.size() > 0) {
//
            adapter = new ClassifyListAdapter(ShopTypeActivity.this,items);
//            //设置适配器
            gvShopType.setAdapter(adapter);
            //添加数据

        }

    }

    private void showPopupWindow(View view) {

        // 设置按钮的点击事件
        if (text1 == null && text2 == null && text3 == null
                && text4 == null && text5 == null&& text6 == null) {

            text1 = (TextView) contentView.findViewById(R.id.text1);
            text2 = (TextView) contentView.findViewById(R.id.text2);
            text3 = (TextView) contentView.findViewById(R.id.text3);
            text4 = (TextView) contentView.findViewById(R.id.text4);
            text5 = (TextView) contentView.findViewById(R.id.text5);
            text6 = (TextView) contentView.findViewById(R.id.text6);
        }


        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        // TODO: 2016/5/17 设置可以获取焦点
        popupWindow.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        popupWindow.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        popupWindow.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        //        popupWindow.showAsDropDown(btnPopup, );
        popupWindow.showAsDropDown(llPriceCheck, 0, 0);
        //popupWindow打开的时候菜单按钮的图标就改变

        /**
         * 如果第一次打开popuwindow ，temptext为空则显示默认推荐为选择状态
         */
        if (temptext == null) {
            text1.setSelected(true);
            temptext = text1;
        } else {
            temptext.setSelected(true);
        }


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "默认",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text1);
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最多",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text2);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最受欢迎",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text3);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最新推荐",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text4);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最新加入",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text5);
            }
        });
    }

    /**
     * 显示popuwindow选中哪个
     *
     * @param text
     */
    private void selectorText(TextView text) {
        if (temptext == text) {
            popupWindow.dismiss();
        } else {
            temptext.setSelected(false);
            text.setSelected(true);
            popupWindow.dismiss();

            temptext = text;
            //从新联网
//            this.url = url;
            //getDataNet();
        }
    }
}
