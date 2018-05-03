package com.weihui.openweibo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.weihui.openweibo.app.AppDelegate;

public class SpHelper {


    static final String SP_CONTENT = "SP_CONTENT";

    private static SharedPreferences.Editor getEditor() {

        return AppDelegate.getContext().getSharedPreferences(SP_CONTENT, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getSp() {


        return AppDelegate.getContext().getSharedPreferences(SP_CONTENT, Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {

        getEditor().putString(key, value).commit();

    }

    public static String getString(String key) {
        return getSp().getString(key, "");
    }
}
