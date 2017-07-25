package com.atguigu.granaryaidi.view.Activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.common.NetLink;
import com.umeng.analytics.MobclickAgent;

import butterknife.InjectView;
import butterknife.OnClick;

public class ShopWebviewActivity extends BaseActivity {


    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

//    private ShopHomeBean.DataBean.ItemsBean.ListBean.OneBean bean;

    @Override
    public void initListener() {
        //防止调起系统的浏览器
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }

                return true;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                progressbar.setVisibility(View.GONE);//在这里写经常会出现崩溃
            }
        });
    }

    @Override@SuppressLint("JavascriptInterface")
    public void initData() {
        String htmlurl = getIntent().getStringExtra(NetLink.HTML_URL);
        String title = getIntent().getStringExtra(NetLink.HTML_TITLE);

        tvTitle.setText(title);
//        bean = (ShopHomeBean.DataBean.ItemsBean.ListBean.OneBean) getIntent().getSerializableExtra("html");

        //设置相关参数
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        //加载页面
        webview.loadUrl(htmlurl);
        Log.e("TAG", "加载html链接==" + htmlurl);

        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_webview;
    }


    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        //点击返回
        finish();

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
