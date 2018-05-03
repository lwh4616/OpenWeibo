package com.weihui.openweibo.model.impl;


import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;
import com.weihui.openweibo.model.ITimeLineRemoteModel;
import com.weihui.openweibo.util.RetrofitHelper;
import com.weihui.openweibo.webapi.WeiboApi;
import com.weihui.openweibo.webapi.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weihui on 2018/4/9.
 */

public class TimeLineRemoteModelImpl implements ITimeLineRemoteModel {

    private static final String TAG = TimeLineRemoteModelImpl.class.getSimpleName();

    public Observable<Status> createFav(long id) {

        String token = Constants.token.getToken();

        return RetrofitHelper.client().create(WeiboApi.class)
                .createFav(id, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    public Observable<Status> destroyFav(long id) {

        String token = Constants.token.getToken();

        return RetrofitHelper.client().create(WeiboApi.class)
                .destroyFav(id, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }


    public Observable<TimeLine> loadHomeTimeLine(long max_id) {

        return RetrofitHelper.client().create(WeiboApi.class)
                .loadHomeTimeLine(max_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    public Observable<TimeLine> fetchHomeTimeLine(long since_id) {

        return RetrofitHelper.client().create(WeiboApi.class)
                .fetchHomeTimeLine(since_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }


}
