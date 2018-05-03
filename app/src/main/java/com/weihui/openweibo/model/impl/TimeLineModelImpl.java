package com.weihui.openweibo.model.impl;

import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;
import com.weihui.openweibo.entity.db.TimeLineDao;
import com.weihui.openweibo.entity.db.User;
import com.weihui.openweibo.model.ITimeLineDBModel;
import com.weihui.openweibo.util.DBManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TimeLineModelImpl implements ITimeLineDBModel {


    public Observable<TimeLine> getDbTimeLine() {
        return Observable.create(new ObservableOnSubscribe<TimeLine>() {

            @Override
            public void subscribe(ObservableEmitter<TimeLine> emitter) {

                TimeLine timeLine = DBManager.getDaoSession().getTimeLineDao().queryBuilder().limit(1).orderDesc(TimeLineDao.Properties.Since_id).unique();

                prepareTimeLine(emitter, timeLine);

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 查询比max_id小的最近一条
     *
     * @param max_id
     * @return
     */
    public Observable<TimeLine> getMoreTimeLine(final long max_id) {

        return Observable.create(new ObservableOnSubscribe<TimeLine>() {

            @Override
            public void subscribe(ObservableEmitter<TimeLine> emitter) {

                TimeLine timeLine = DBManager.getDaoSession().getTimeLineDao().queryBuilder().
                        where(TimeLineDao.Properties.Since_id.le(max_id))
                        .orderDesc(TimeLineDao.Properties.Since_id).limit(1).unique();

                prepareTimeLine(emitter, timeLine);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    public void saveStatus(List<Status> list) {

        DBManager.getDaoSession().getStatusDao().insertOrReplaceInTx(list);
    }


    public void saveTimeLine(TimeLine timeLine) {

        if (null == timeLine.statuses || timeLine.statuses.isEmpty()) {
            return;
        }

        DBManager.getDaoSession().getTimeLineDao().insertOrReplace(timeLine);
    }


    public void saveUser(List<User> list) {
        DBManager.getDaoSession().getUserDao().insertOrReplaceInTx(list);
    }


    private void prepareTimeLine(ObservableEmitter<TimeLine> emitter, TimeLine timeLine) {
        if (null != timeLine) {
            List<Status> statuses = timeLine.getStatuses();
            if (null != statuses) {
                for (Status status : statuses) {
                    status.getUser();
                    status.formatTime();
                }
            }
            timeLine.saveDb = false;
            emitter.onNext(timeLine);
        } else {
            emitter.onComplete();
        }
    }


}
