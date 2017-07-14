package com.atguigu.granaryaidi.view.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.GoodsDetailsBean;
import com.atguigu.granaryaidi.cart.CartStorage;
import com.atguigu.granaryaidi.cart.GoodsBean;
import com.atguigu.granaryaidi.utils.DensityUtil;
import com.atguigu.granaryaidi.view.viewmyself.AddSubView;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsGotoBuyActivity extends BaseActivity {

    @InjectView(R.id.brand_name)
    TextView brandName;
    @InjectView(R.id.tv_product_name)
    TextView tvProductName;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_product_type)
    TextView tvProductType;
    @InjectView(R.id.ll_product_type)
    LinearLayout llProductType;
    @InjectView(R.id.nas_goodinfo_num)
    AddSubView nasGoodinfoNum;
    @InjectView(R.id.iv_brand_buy_logo)
    ImageView ivBrandBuyLogo;
    @InjectView(R.id.tv_ensure)
    TextView tvEnsure;
    @InjectView(R.id.tv_dimis)
    TextView tvDimis;

    /**
     * 上层页面传来的数据
     * <p>
     * 该页面打开的时候需要判断是从哪个按键被打开的，是“加入购物车”还是“直接购买”
     */
    private GoodsDetailsBean.DataBean.ItemsBean items;

    /**
     * 数据库的对象
     */
    private CartStorage cartStorage;


    /**
     * 转场动画
     */
    @Override
    public void activityAnmotion() {
        super.activityAnmotion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //方法二,从下边进来
            Transition explode = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_bottom);
            getWindow().setEnterTransition(explode);
        }
    }

    @Override
    public void initListener() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void initData() {

        /**
         * 设置数据
         */
        if (items != null) {
            Glide.with(GoodsGotoBuyActivity.this)
                    .load(items.getGoods_image())
                    .into(ivBrandBuyLogo);
            if(items.getBrand_info() != null) {

                brandName.setText(items.getBrand_info().getBrand_name());
            }
            tvProductName.setText(items.getGoods_name());
//            if (TextUtils.isEmpty(items.getDiscount_price())) {
//                tvPrice.setText("￥" + items.getPrice());
//            } else {
//                tvPrice.setText("￥" + items.getDiscount_price());
//            }

            if(items.getSku_inv().size() > 0 && items.getSku_inv() != null) {//针对个别商品艺术品
                if(TextUtils.isEmpty(items.getSku_inv().get(0).getDiscount_price())) {
                    tvPrice.setText("￥" + items.getSku_inv().get(0).getPrice());
                }else {
                    tvPrice.setText("￥" + items.getSku_inv().get(0).getDiscount_price());

                }
                //第一行的类型先设置了后边动态new
                tvProductType.setText(items.getSku_info().get(0).getType_name());

//            设置可购买的类型
                setllProductTypeData();

            }else {
                tvPrice.setText("￥" + items.getPrice());

            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setllProductTypeData() {

        /**
         * 放第一种类型的数据
         */
        LinearLayout linearLayout1 = getTypeLinearLayout(0);
        llProductType.addView(linearLayout1);


        /**
         * 设置第二行以后的类型数据
         */
        if (items.getSku_info().size() > 1) {

            for (int i = 1; i < items.getSku_info().size(); i++) {

                LinearLayout typeLinearLayout = getTypeLinearLayout(i);

                llProductType.addView(typeLinearLayout);
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    private LinearLayout getTypeLinearLayout(int id) {
        /**
         * 设置每行类型的标题
         */
        if (id != 0) {
            TextView text1 = new TextView(GoodsGotoBuyActivity.this);
            LinearLayout.LayoutParams lpp1 = new LinearLayout.LayoutParams(//字体高30sp
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            text1.setLayoutParams(lpp1);
            lpp1.setMargins(0, 10, 0, 10);

            text1.setTextColor(Color.parseColor("#C5C5C5"));
            text1.setTextSize(15);
            text1.setText(items.getSku_info().get(id).getType_name());
            llProductType.addView(text1);
        }


        /**
         * 设置具体没行内容----------------------------------------
         */
        RadioGroup group = new RadioGroup(this);

        RadioGroup groupend = addview(group, id);

        return groupend;
    }

    @Override
    public void initView() {
        cartStorage = CartStorage.getInstance(this);

        items = (GoodsDetailsBean.DataBean.ItemsBean) getIntent().getSerializableExtra("goodsbean");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_goto_buy;
    }


    @OnClick({R.id.tv_dimis, R.id.tv_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dimis:
                //用系统的返回方法
                onBackPressed();

                break;
            case R.id.tv_ensure:
//                Log.e("buy","hashsize==" + hashMap.size());
                //点击确定加入购物车或者直接购买
                //然后页面返回后跳转...

                //测试数据保存到数据库
                //String type, String cover_price, String figure, String name, String product_id, int number
                ensureToBuy();

                if (cartStorage != null && items != null) {
                    if(TextUtils.isEmpty(price)) {
                        GoodsBean bean = new GoodsBean(
                                items.getSku_info().get(0).getAttrList().get(0).getAttr_name()
                                , items.getPrice(), items.getGoods_image(), items.getGoods_name(),
                                items.getGoods_id(), nasGoodinfoNum.getValue()
                        );
                        cartStorage.addData(bean);
                        if(TextUtils.isEmpty(items.getSku_inv().get(0).getDiscount_price())) {
                            tvPrice.setText("￥" + items.getSku_inv().get(0).getPrice());
                        }else {
                            tvPrice.setText("￥" + items.getSku_inv().get(0).getDiscount_price());

                        }
                    }else {//一般都会进这里
                        //attr_idcart为获取相应价格的id
                        if(items.getGoods_id().length() > 2) {//一般进这里
                            String subid = items.getGoods_id().substring(items.getGoods_id().length() - 2);
                            GoodsBean bean = new GoodsBean(
                                    typename
                                    , price, items.getGoods_image(), items.getGoods_name(),
                                    subid + attr_idcart, nasGoodinfoNum.getValue()
                            );
                            cartStorage.addData(bean);

                        }else {
                            GoodsBean bean = new GoodsBean(
                                    typename
                                    , price, items.getGoods_image(), items.getGoods_name(),
                                    items.getGoods_id() + attr_idcart, nasGoodinfoNum.getValue()
                            );
                            cartStorage.addData(bean);
                        }
                        tvPrice.setText("￥" + price);
                    }

                }
                onBackPressed();
                showToast("添加购物车成功");
                break;
        }
    }

    /**
     * //点击确定加入购物车或者直接购买
     * //然后页面返回后跳转...
     * //测试数据保存到数据库
     */
    private String price = "";
    private String priceid = "";
    private String attr_idcart = "";//记得初始值为""，不然下边会与null叠加
    private String typename = "";

    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    private void ensureToBuy() {
        attr_idcart = "";

        if (cartStorage != null && items != null) {
            if (hashMap.size() == items.getSku_info().size()) {

                //items.getSku_info().get(id).getAttrList().get(i).getAttr_id()
                //id 为 key ，i 为value,遍历HashMap
                String attr_id = "";
                Iterator iter = hashMap.entrySet().iterator();
                String typename = "";
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Integer key = (Integer) entry.getKey();
                    Integer val = (Integer) entry.getValue();
                    Log.e("buy","key,val===" + key + "," + val);
                    attr_id += items.getSku_info().get(key).getAttrList().get(val).getAttr_id() + ",";

                    //用于拼接商品的具体名称
                    typename += items.getSku_info().get(key).getType_name() + ":"
                            + items.getSku_info().get(key).getAttrList().get(val).getAttr_name() + ";";

                    //用于存到数据库，作为商品的主键拼接用
                    attr_idcart += items.getSku_info().get(key).getAttrList().get(val).getAttr_id();
                }
                this.typename = typename;

//                Log.e("buy", "attr_id===" + attr_id);
                priceid = attr_id.substring(0, attr_id.length() - 1);//去掉最后一个逗号
                Log.e("buy", "priceid===" + priceid);
                Log.e("buy", "hashmapsize===" + hashMap.size());

                //再拿priceid与价格的集合去比较id的到具体的价格
                List<GoodsDetailsBean.DataBean.ItemsBean.SkuInvBean> sku_inv = items.getSku_inv();

                for (int i = 0; i < sku_inv.size(); i++) {

                    if (priceid.equals(sku_inv.get(i).getAttr_keys())) {
                        //匹配价格成功
                        price = sku_inv.get(i).getPrice();
                    }

                }

                if(TextUtils.isEmpty(price)) {
                    tvPrice.setText("￥" + items.getPrice());
                }else {
                    tvPrice.setText("￥" + price);
                }
                Log.e("buy", "priceid,price===" + priceid + "," + price);
//                GoodsBean bean = new GoodsBean(
//                        items.getSku_info().get(0).getAttrList().get(0).getAttr_name()
//                        , items.getPrice(), items.getGoods_image(), items.getGoods_name(),
//                        items.getGoods_id(), nasGoodinfoNum.getValue()
//                );
//                cartStorage.addData(bean);
//                showToast("添加购物车成功");

            } else {
                showToast("请选择商品类型");
            }
        }
    }

    //动态添加视图
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public RadioGroup addview(RadioGroup radiogroup, int id) {//id 代表类型的第几行

        //设置RadioGroup中的RadioButton水平排列
        radiogroup.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < items.getSku_info().get(id).getAttrList().size(); i++) {
            final List<GoodsDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList
                    = items.getSku_info().get(id).getAttrList();

            RadioButton button = new RadioButton(GoodsGotoBuyActivity.this);
            setRaidBtnAttribute(button, attrList.get(i).getAttr_name(), i, id);//i 代表每行的第几个

            radiogroup.addView(button);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, DensityUtil.dip2px(GoodsGotoBuyActivity.this, 10), 0);//4个参数按顺序分别是左上右下

            button.setPadding(DensityUtil.dip2px(GoodsGotoBuyActivity.this, 5),
                    0, DensityUtil.dip2px(GoodsGotoBuyActivity.this, 5), 0);

            button.setLayoutParams(layoutParams);
        }

        return radiogroup;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setRaidBtnAttribute(final RadioButton codeBtn, String btnContent, final int i, final int id) {
        if (null == codeBtn) {//id 代表类型的第几行//i 代表每行的第几个
            return;
        }
        codeBtn.setBackgroundResource(R.drawable.goods_gobuy);
        codeBtn.setTextColor(Color.WHITE);
        codeBtn.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        //codeBtn.setTextSize( ( textSize > 16 )?textSize:24 );
        codeBtn.setId(i);
        codeBtn.setTextSize(10);
        codeBtn.setText(btnContent);
        //codeBtn.setPadding(2, 0, 2, 0);
        if (i == 0) {
            codeBtn.setChecked(true);
        }
        codeBtn.setGravity(Gravity.CENTER);
//        Log.e("buy", "id--i---" + id + i);

        hashMap.put(id, 0);//默认选中 每一行的第一个类型

        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(GoodsGotoBuyActivity.this, codeBtn.getText().toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(GoodsGotoBuyActivity.this,
//                        items.getSku_info().get(id).getAttrList().get(i).getAttr_id(),
//                        Toast.LENGTH_SHORT).show();
                //id 为 行号，getAttr_id（）；id和i都是从0开始
                Toast.makeText(GoodsGotoBuyActivity.this, id + "," + i, Toast.LENGTH_SHORT).show();
                hashMap.put(id, i);
                ensureToBuy();
            }
        });
        //DensityUtilHelps.Dp2Px(this,40)
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , DensityUtil.dip2px(GoodsGotoBuyActivity.this, 25));
        codeBtn.setLayoutParams(rlp);
    }
}
