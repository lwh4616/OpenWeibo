package com.weihui.openweibo.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.util.Log;

import com.google.gson.Gson;
import com.weihui.openweibo.entity.ErrorBean;
import com.weihui.openweibo.entity.LoadBean;
import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;
import com.weihui.openweibo.entity.db.User;
import com.weihui.openweibo.model.ITimeLineDBModel;
import com.weihui.openweibo.model.ITimeLineRemoteModel;
import com.weihui.openweibo.model.ModelFactory;
import com.weihui.openweibo.vm.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by weihui on 2018/4/9.
 */

public class HomeViewModel extends BaseViewModel {

    private static final String TAG = "HomeViewModel";

    ITimeLineRemoteModel service;

    ITimeLineDBModel daoModel;

    public ObservableField<Boolean> fetcherHome = new ObservableField<>(false);

    public ObservableList<Status> statuses = new ObservableArrayList<>();

    public LoadBean loadBean = new LoadBean();

    public ObservableField<String> erroMsg = new ObservableField<>("");

    private long since_id = 0;

    private long max_id = 0;

    private long lastFetchTime = 0;

    public HomeViewModel() {
        service = ModelFactory.getInstance().getTimeLineRemoteModel();
        daoModel = ModelFactory.getInstance().getTimeLineDBModel();
    }


    public void loadTimeLine() {

        daoModel.getDbTimeLine()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatWith(service.fetchHomeTimeLine(since_id))
                .subscribe(getConsumer(), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handException(throwable);
                        fetcherHome.set(false);

                    }
                }, new Action() {
                    @Override
                    public void run() {
                        fetcherHome.set(false);
                    }
                });

    }


    private Consumer<TimeLine> getConsumer() {
        return new Consumer<TimeLine>() {
            @Override
            public void accept(TimeLine timeLine) {

                if (null == timeLine || null == timeLine.statuses || timeLine.statuses.isEmpty()) {
                    fetcherHome.set(false);
                    return;
                }

                if (timeLine.saveDb) {
                    saveTimeLine(timeLine);
                }

                if (timeLine.since_id > since_id) {
                    since_id = timeLine.since_id;
                }

                statuses.addAll(0, timeLine.statuses);
                fetcherHome.set(false);

            }
        };
    }


    /**
     * 往前查找 先查找本地的
     */
    public void loadMoreStatus() {
        loadBean.setMsg("Loading...");
        loadBean.setLoadDone(false);
        loadBean.setLoadError(false);


        max_id = statuses.get(statuses.size() - 1).getId() - 1;

        Log.d(TAG, "since_id:" + since_id + " max_id:" + max_id);

        daoModel.getMoreTimeLine(max_id).concatWith(service.loadHomeTimeLine(max_id))

                .subscribe(new Consumer<TimeLine>() {
                    @Override
                    public void accept(TimeLine timeLine) throws Exception {


                        if (null == timeLine.statuses || timeLine.statuses.isEmpty()) {
                            Log.e(TAG, "all load done" + max_id + ":size " + statuses.size());
                            max_id = -1;
                            fetcherHome.set(false);
                            loadBean.setLoadError(true);
                            loadBean.setMsg("全部加载完成");
                            return;
                        }

                        if (timeLine.saveDb) {
                            saveTimeLine(timeLine);
                        }

                        loadBean.setLoadDone(true);
                        if (null != timeLine.statuses && timeLine.statuses.size() > 0) {
                            statuses.addAll(timeLine.statuses);
                        }
                        fetcherHome.set(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        handException(throwable);
                        loadBean.setLoadError(true);
                        loadBean.setMsg("网络异常");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
//                        loadBean.setLoadDone(true);
                    }
                });

    }


    private void handException(Throwable throwable) {

        Log.e(TAG, throwable.toString());

        if (throwable instanceof HttpException) {
            try {
                HttpException exception = (HttpException) throwable;
                String error = exception.response().errorBody().string();

                ErrorBean bean = new Gson().fromJson(error, ErrorBean.class);
                erroMsg.set(bean.error + " code:" + bean.error_code);

                Log.e(TAG, error);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }

        }

    }

    private void saveTimeLine(TimeLine timeLine) {
        timeLine.setId(System.currentTimeMillis());
        Log.e(TAG, "timeLine:" + timeLine.getSince_id() + ":" + timeLine.getMax_id() + " size :" + timeLine.statuses.size() + ":total" + timeLine.total_number);
        List<User> users = null;
        if (null != timeLine.statuses) {
            users = new ArrayList<>(timeLine.statuses.size());
            for (Status status : timeLine.statuses) {
                status.prepareWithTimeLine(timeLine);
                if (null != status.user) {
                    status.setUser(status.user);
                    users.add(status.user);
                }
            }
        }


        daoModel.saveTimeLine(timeLine);
        if (null != users) {
            daoModel.saveUser(users);
        }
        if (null != timeLine.statuses) {
            daoModel.saveStatus(timeLine.statuses);
        }

    }

    public void destroyFav(long id, Consumer<Status> success, Consumer<Throwable> error) {
        service.destroyFav(id).subscribe(success, error);
    }


    public void createFav(long id, Consumer<Status> success, Consumer<Throwable> error) {
        service.createFav(id).subscribe(success, error);
    }

    public void fetchHomeTimeLine() {

        //最小间隔5秒
        if (System.currentTimeMillis() - lastFetchTime < 5 * 1000) {
            fetcherHome.set(false);
            return;
        }

        lastFetchTime = System.currentTimeMillis();
        service.fetchHomeTimeLine(since_id).subscribe(getConsumer(), new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                handException(throwable);
                fetcherHome.set(false);

            }
        });

    }
}
