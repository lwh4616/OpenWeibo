package com.weihui.openweibo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weihui.openweibo.vm.base.BaseViewModel;

/**
 * Created by weihui on 2018/4/9.
 */

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {


    protected V binding;

    protected VM viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(), container, false);
        viewModel = initViewModel();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewObservable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel = null;
    }

    public abstract int initContentView();

    public abstract VM initViewModel();

    public abstract void initViewObservable();


}
