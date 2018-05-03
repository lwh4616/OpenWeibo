package com.weihui.openweibo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.weihui.openweibo.R;
import com.weihui.openweibo.ui.base.BaseRecyclerViewAdapter;
import com.weihui.openweibo.ui.adapter.handler.StatusItemHandler;
import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.databinding.SnsItemBinding;
import com.weihui.openweibo.vm.HomeViewModel;

import java.util.List;

/**
 * Created by weihui on 2018/4/9.
 */

public class SnsAdapter extends BaseRecyclerViewAdapter<SnsItemBinding, Status, HomeViewModel> {


    public SnsAdapter(Context mContext, List<Status> datas, HomeViewModel model) {
        super(mContext, datas);
        viewModel = model;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.sns_item;
    }


    @Override
    protected void onClickLoad(View view) {

        viewModel.loadMoreStatus();

    }

    @Override
    public BaseRecyclerViewHolder makeViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder holder = super.makeViewHolder(parent, viewType);

        SnsItemBinding binding = holder.getBinding();
        binding.setHandler(new StatusItemHandler(viewModel));

        return holder;
    }

    public Status getItem(int position) {
        return datas.get(position);
    }


    public void onBindViewHolder(SnsItemBinding binding, Status itemData, RecyclerView.ViewHolder holder, int position) {
        binding.getHandler().bindData(position, itemData);
        binding.setSns(itemData);
        binding.executePendingBindings();
    }


}
