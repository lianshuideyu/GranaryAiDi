package com.atguigu.granaryaidi.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/7/5.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

    }

    public static Context getContext(){
        return context;
    }
}
