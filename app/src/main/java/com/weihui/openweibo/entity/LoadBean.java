package com.weihui.openweibo.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.weihui.openweibo.BR;

import java.io.Serializable;

public class LoadBean extends BaseObservable implements Serializable {


    @Bindable
    private boolean loadDone;

    @Bindable
    private boolean loadError;

    @Bindable
    private String msg="Loading...";

    public boolean isLoadDone() {
        return loadDone;
    }

    public void setLoadDone(boolean loadDone) {
        this.loadDone = loadDone;
        notifyPropertyChanged(BR.loadDone);
    }

    public boolean isLoadError() {
        return loadError;
    }

    public void setLoadError(boolean loadError) {
        this.loadError = loadError;
        notifyPropertyChanged(BR.loadError);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(BR.msg);
    }
}
