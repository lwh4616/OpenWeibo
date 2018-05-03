package com.weihui.openweibo.util;

import android.database.sqlite.SQLiteDatabase;

import com.weihui.openweibo.app.AppDelegate;
import com.weihui.openweibo.entity.db.DaoMaster;
import com.weihui.openweibo.entity.db.DaoSession;

public class DBManager {

    private static DaoSession mDaoSession;

    public static void initDd() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(AppDelegate.getContext(), "weibo-db", null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
