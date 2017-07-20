package com.atguigu.granaryaidi.common;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/7/5.
 */

public class MyApplication extends Application {

    private static Context context;

    public static ShareSDK myShareSDK;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        /**
         * okhttp联网初始化
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        //初始化shareSDk
        MobSDK.init(this);

        //极光推送初始化
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        //初始化sharesdk的登录
//        myShareSDK = new ShareSDK();
    }

    public static Context getContext(){
        return context;
    }
}
