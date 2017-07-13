package com.atguigu.granaryaidi.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.bean.DaRenDefaultBean;
import com.atguigu.granaryaidi.common.NetLink;
import com.atguigu.granaryaidi.utils.HttpUtils;
import com.atguigu.granaryaidi.view.Activity.DarenDetailsActivity;
import com.atguigu.granaryaidi.view.adapter.DaRenDefaultAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.gv_daren_default)
    GridView gvDarenDefault;
    @InjectView(R.id.ib_shop_search)
    ImageButton ibShopSearch;
    @InjectView(R.id.ib_shop_menu)
    ImageButton ibShopMenu;

    @InjectView(R.id.rl_basetitle)
    RelativeLayout rlbasetitle;


    /**
     * 联网获取数据
     */
    private List<DaRenDefaultBean.DataBean.ItemsBean> items;
    private DaRenDefaultAdapter adapter;

    private String url;

    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("达人");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daren_main;
    }

    @Override
    protected void initView() {
        ibShopMenu.setVisibility(View.VISIBLE);
        ibShopSearch.setVisibility(View.VISIBLE);

        ibShopMenu.setBackgroundResource(R.drawable.actionbar_navigation_menu);

        /**
         * 初始化popuwindow 相关
         */
        contentView = LayoutInflater.from(context).inflate(
                R.layout.pop_window, null);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        url = NetLink.DAREN_DEFAULT;
    }

    @Override
    protected void initData() {
        //联网请求数据
        if (!TextUtils.isEmpty(url)) {

            getDataNet();
        }
    }

    @Override
    protected void initListener() {

        //GridView的点击事件
        gvDarenDefault.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //将bean对象传过去
                Intent intent = new Intent(context, DarenDetailsActivity.class);
//                intent.putExtra(NetLink.DAREN_DETAILS,items.get(position));
                intent.putExtra("uid", items.get(position).getUid());
                intent.putExtra("username", items.get(position).getUsername());//达人名称
                intent.putExtra("duty", items.get(position).getDuty());//行业
                intent.putExtra("orig", items.get(position).getUser_images().getOrig());//头像链接

                startActivity(intent);
            }
        });

        /**
         * popuwindow 消失的监听，当popuwindow消失的时候方法会回调
         */
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                Toast.makeText(context, "00", Toast.LENGTH_SHORT).show();
                ibShopMenu.setBackgroundResource(R.drawable.actionbar_navigation_menu);
            }
        });

    }

    private void getDataNet() {

        HttpUtils.getInstance().get(url, new HttpUtils.MyHttpClickListener() {
            @Override
            public void onSuccess(String content) {
                Log.e("DaRen", "联网成功==" + content);
                if (!TextUtils.isEmpty(content)) {
                    //解析数据
                    processData(content);
                }
            }

            @Override
            public void onFailure(String content) {
                Log.e("DaRen", "联网失败==" + content);
            }
        });
    }

    /**
     * 解析数据
     */
    private void processData(String content) {
        DaRenDefaultBean bean = new Gson().fromJson(content, DaRenDefaultBean.class);
        items = bean.getData().getItems();

        Log.e("DaRen", "解析==" + items.get(0).getUsername());

        if (items != null && items.size() > 0) {

            adapter = new DaRenDefaultAdapter(context, items);
            //设置适配器
            gvDarenDefault.setAdapter(adapter);

        }

    }

    @OnClick({R.id.ib_shop_search, R.id.ib_shop_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_shop_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_shop_menu:

                showPopupWindow(view);


                break;
        }
    }

    /**
     * 用于缓存popuwindow中的textview
     * 以下写成全局的保证为同一个对象
     */
    private TextView temptext;
    // 一个自定义的布局，作为显示的内容
    View contentView;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    PopupWindow popupWindow;

    private void showPopupWindow(View view) {


        // 设置按钮的点击事件
        if (text1 == null && text2 == null && text3 == null && text4 == null && text5 == null) {

            text1 = (TextView) contentView.findViewById(R.id.text1);
            text2 = (TextView) contentView.findViewById(R.id.text2);
            text3 = (TextView) contentView.findViewById(R.id.text3);
            text4 = (TextView) contentView.findViewById(R.id.text4);
            text5 = (TextView) contentView.findViewById(R.id.text5);
        }


        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313335")));

        // TODO: 2016/5/17 设置可以获取焦点
        popupWindow.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        popupWindow.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        popupWindow.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        //        popupWindow.showAsDropDown(btnPopup, );
        popupWindow.showAsDropDown(rlbasetitle, 0, 0);
        //popupWindow打开的时候菜单按钮的图标就改变
        ibShopMenu.setBackgroundResource(R.drawable.close);

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
                selectorText(text1, NetLink.DAREN_DEFAULT);
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最多",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text2, NetLink.DAREN_MOST_RECOMMEND);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最受欢迎",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text3, NetLink.DAREN_MOST_WELCOME);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最新推荐",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text4, NetLink.DAREN_NEW_RECOMMEND);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "最新加入",
//                        Toast.LENGTH_SHORT).show();
                selectorText(text5, NetLink.DAREN_NEW_JOIN);
            }
        });
    }

    /**
     * 显示popuwindow选中哪个
     *
     * @param text
     * @param url
     */
    private void selectorText(TextView text, String url) {
        if (temptext == text) {
            popupWindow.dismiss();
        } else {
            temptext.setSelected(false);
            text.setSelected(true);
            popupWindow.dismiss();

            temptext = text;
            //从新联网
            this.url = url;
            getDataNet();
        }
    }
}
