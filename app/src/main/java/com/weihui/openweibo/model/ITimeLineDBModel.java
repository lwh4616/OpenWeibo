package com.weihui.openweibo.model;

import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;
import com.weihui.openweibo.entity.db.User;

import java.util.List;

import io.reactivex.Observable;

public interface ITimeLineDBModel {

    Observable<TimeLine> getDbTimeLine();

    Observable<TimeLine> getMoreTimeLine(final long max_id);

    void saveTimeLine(TimeLine timeLine);

    void saveUser(List<User> list);

    void saveStatus(List<Status> list);
}
