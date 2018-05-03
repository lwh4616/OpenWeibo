package com.weihui.openweibo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weihui.openweibo.vm.base.BaseViewModel;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {


    protected V binding;

    protected VM viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initParam();

        initViewDataBinding();

        initViewObservable();

        initData();
    }

    /**
     * 处理 intent等参数
     */
    protected void initParam() {

    }

    /**
     * 数据初始化
     */
    protected void initData() {

    }


    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, initContentView());
        viewModel = initViewModel();
    }


    protected abstract int initContentView();

    protected abstract VM initViewModel();

    protected abstract void initViewObservable();


}

