package com.weihui.openweibo.ui.adapter.handler;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weihui.openweibo.app.AppDelegate;
import com.weihui.openweibo.entity.ErrorBean;
import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.vm.HomeViewModel;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

public class StatusItemHandler {


    private int position;

    private Status status;


    private HomeViewModel viewModel;


    public StatusItemHandler(HomeViewModel model) {
        this.viewModel = model;
    }


    public void bindData(int position, Status status) {
        this.position = position;
        this.status = status;
    }



    public void  onLoad(View view)
    {
        viewModel.loadMoreStatus();
    }

    public void clickFav(View view) {

        if (!status.isFavorited()) {
            status.setFavorited(true);
            viewModel.createFav(status.getId(), new Consumer<Status>() {
                @Override
                public void accept(Status s) {
                    status.setFavorited(true);

                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                    String errMsg = "收藏失败";
                    if (throwable instanceof HttpException) {
                        HttpException exception = (HttpException) throwable;

                        String result = exception.response().errorBody().string();
                        ErrorBean errorBean = new Gson().fromJson(result, ErrorBean.class);
                        Log.d("TAG", result);
                        if (errorBean.error_code == 20704) {
                            return;
                        } else {
                            errMsg = errorBean.error;
                        }

                    }
                    status.setFavorited(false);
                    Toast.makeText(AppDelegate.getContext(), errMsg, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            status.setFavorited(false);
            viewModel.destroyFav(status.getId(), new Consumer<Status>() {
                @Override
                public void accept(Status s) {
                    status.setFavorited(false);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                    String errMsg = "取消收藏失败";
                    if (throwable instanceof HttpException) {
                        HttpException exception = (HttpException) throwable;
                        String result = exception.response().errorBody().string();
                        ErrorBean errorBean = new Gson().fromJson(result, ErrorBean.class);
                        Log.d("TAG", result);
//                        if (errorBean.error_code == 20704) {
//                            return;
//                        } else {
//                            errMsg = errorBean.error;
//                        }
                        errMsg = errorBean.error;
                    }
                    status.setFavorited(true);
                    Toast.makeText(AppDelegate.getContext(), errMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }

        Log.d("TAG", position + ":" + status);

    }
}
