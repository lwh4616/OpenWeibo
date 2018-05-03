package com.weihui.openweibo.ui.fragments.maintab;

import com.weihui.openweibo.R;
import com.weihui.openweibo.databinding.FragmentMessageBinding;
import com.weihui.openweibo.ui.base.BaseFragment;
import com.weihui.openweibo.vm.base.BaseViewModel;

/**
 * Created by weihui on 2018/4/9.
 */

public class MessageFragment extends BaseFragment<FragmentMessageBinding, BaseViewModel> {

    @Override
    public int initContentView() {
        return R.layout.fragment_message;
    }

    @Override
    public BaseViewModel initViewModel() {
        return null;
    }

    @Override
    public void initViewObservable() {

    }

}
