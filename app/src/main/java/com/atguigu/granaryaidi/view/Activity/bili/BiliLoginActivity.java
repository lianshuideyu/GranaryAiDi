package com.atguigu.granaryaidi.view.Activity.bili;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.granaryaidi.Base.BaseActivity;
import com.atguigu.granaryaidi.R;

import java.util.HashMap;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class BiliLoginActivity extends BaseActivity {


    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.delete_username)
    ImageButton deleteUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.iv_qq)
    ImageView ivQq;
    @InjectView(R.id.iv_qqzone)
    ImageView ivQqzone;
    @InjectView(R.id.iv_sina)
    ImageView ivSina;
    @InjectView(R.id.iv_wxchat)
    ImageView ivWxchat;
    @InjectView(R.id.iv_wxmoment)
    ImageView ivWxmoment;


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bili_login;
    }


    @OnClick({R.id.tv_back, R.id.tv_forgetpassword, R.id.delete_username, R.id.btn_login, R.id.iv_qq, R.id.iv_qqzone, R.id.iv_sina, R.id.iv_wxchat, R.id.iv_wxmoment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();

                break;
            case R.id.tv_forgetpassword:
                break;
            case R.id.delete_username:
                break;
            case R.id.btn_login:
                break;
            case R.id.iv_qq:
                showToast("使用qq登录");
                Login(QQ.NAME);

                break;
            case R.id.iv_qqzone:
                break;
            case R.id.iv_sina:
                Login(SinaWeibo.NAME);

                break;
            case R.id.iv_wxchat:
                Login(Wechat.NAME);
                showToast("微信登录");
                break;
            case R.id.iv_wxmoment:
                break;
        }
    }

    /**
     * 登录
     *
     * @param name 登录方式（QQ.NAME、Wechat.NAME、SinaWeibo.NAME）
     */
    private String type = "";

    public void Login(String name) {
        type = "login";
        Platform mPlatform = ShareSDK.getPlatform(name);
        mPlatform.setPlatformActionListener(mPlatformActionListener);
        mPlatform.authorize();//单独授权,OnComplete返回的hashmap是空的
        mPlatform.showUser(null);//授权并获取用户信息

    }

    private PlatformActionListener mPlatformActionListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            if (type.equals("login")) {
                Log.e("onComplete", "登录成功");
                Log.e("openid", platform.getDb().getUserId()); //拿到登录后的
                Log.e("username", platform.getDb().getUserName()); //拿到登录用户的昵称
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("登录成功");
                        finish();
                    }
                });
            }
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e("onError", throwable.toString() + "");
            if (type.equals("login")) {
                Log.e("onError", "登录失败" + throwable.toString());
            }
        }

        @Override
        public void onCancel(Platform platform, int i) {

            if (type.equals("login")) {
                Log.e("onCancel", "登录取消");
            }
        }
    };
}