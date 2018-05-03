package com.weihui.openweibo.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.weihui.openweibo.util.DBManager;

/**
 * Created by weihui on 2018/4/8.
 */

public class AppDelegate extends MultiDexApplication {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DBManager.initDd();
    }


    public static Context getContext() {
        return context;
    }
}
