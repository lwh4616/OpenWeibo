package com.weihui.openweibo.model;

import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;

import io.reactivex.Observable;

public interface ITimeLineRemoteModel {

    Observable<Status> createFav(long id);

    Observable<Status> destroyFav(long id);

    Observable<TimeLine> loadHomeTimeLine(long max_id);

    Observable<TimeLine> fetchHomeTimeLine(long since_id);
}
