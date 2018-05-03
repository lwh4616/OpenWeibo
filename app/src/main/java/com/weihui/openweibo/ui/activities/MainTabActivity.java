package com.weihui.openweibo.ui.activities;

import android.support.v4.app.Fragment;

import com.weihui.openweibo.R;
import com.weihui.openweibo.ui.base.BaseActivity;
import com.weihui.openweibo.ui.adapter.MainTabAdapter;
import com.weihui.openweibo.databinding.ActivityMainTabBinding;
import com.weihui.openweibo.ui.fragments.maintab.HomeFragment;
import com.weihui.openweibo.ui.fragments.maintab.MessageFragment;
import com.weihui.openweibo.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihui on 2018/4/8.
 */

public class MainTabActivity extends BaseActivity<ActivityMainTabBinding, MainViewModel> {

    private static final String TAG = "MainTabActivity";

    List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void initParam() {
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_main_tab;
    }

    @Override
    protected MainViewModel initViewModel() {
        return new MainViewModel();
    }

    @Override
    protected void initViewObservable() {
        binding.setAdapter(new MainTabAdapter(getSupportFragmentManager(), fragments));
    }


    @Override
    protected void initData() {

    }


}
