package com.weihui.openweibo.ui.fragments.maintab;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.weihui.openweibo.BR;
import com.weihui.openweibo.R;
import com.weihui.openweibo.ui.adapter.SnsAdapter;
import com.weihui.openweibo.ui.adapter.handler.EndlessRecyclerOnScrollListener;
import com.weihui.openweibo.databinding.FragmentHomeBinding;
import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.ui.base.BaseFragment;
import com.weihui.openweibo.vm.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihui on 2018/4/9.
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private static final String TAG = "HomeFragment";

    List<Status> statusList = new ArrayList<>();

    FragmentHomeBinding binding;

    SnsAdapter adapter;

    @Override
    public HomeViewModel initViewModel() {
        return new HomeViewModel();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_home;
    }


    @Override
    public void initViewObservable() {

        viewModel.loadBean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

                if (propertyId == BR.loadDone) {
                    if (viewModel.loadBean.isLoadDone()) {
                        binding.snsList.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.loadDone();
                            }
                        }, 300);
                    }
                } else if (propertyId == BR.msg) {
                    adapter.updateLoadMsg(viewModel.loadBean.getMsg(), viewModel.loadBean.isLoadError());
                } else if (propertyId == BR.loadError) {

                    adapter.updateLoadMsg(viewModel.loadBean.getMsg(), viewModel.loadBean.isLoadError());
                }
            }
        });

        viewModel.erroMsg.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

                Toast.makeText(getContext(), viewModel.erroMsg.get(), Toast.LENGTH_SHORT).show();

            }
        });


        viewModel.fetcherHome.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (viewModel.fetcherHome.get()) {
                    viewModel.fetchHomeTimeLine();
                } else {
                    binding.swipeRefresh.setRefreshing(false);
                }
                Log.d(TAG, "fetcherHome  :" + ((ObservableField) observable).get());
            }
        });

        viewModel.statuses.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Status>>() {
            @Override
            public void onChanged(ObservableList<Status> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<Status> sender, int positionStart, int itemCount) {

                for (int index = positionStart; index < positionStart + itemCount; index++) {
                    statusList.set(index, sender.get(index));

                }
                binding.getAdapter().notifyItemRangeChanged(positionStart, itemCount);

            }

            @Override
            public void onItemRangeInserted(ObservableList<Status> sender, int positionStart, int itemCount) {
                binding.swipeRefresh.setRefreshing(false);
                List<Status> list = new ArrayList<>(itemCount);
                for (int index = positionStart; index < positionStart + itemCount; index++) {
                    list.add(sender.get(index));
                }
                statusList.addAll(positionStart, list);
                binding.getAdapter().notifyItemRangeInserted(positionStart, itemCount);

            }

            @Override
            public void onItemRangeMoved(ObservableList<Status> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<Status> sender, int positionStart, int itemCount) {

                for (int index = positionStart; index < positionStart + itemCount; index++) {
                    statusList.remove(index);

                }
                binding.getAdapter().notifyItemRangeRemoved(positionStart, itemCount);

            }
        });


        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.fetcherHome.set(true);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.snsList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        binding.snsList.addItemDecoration(dividerItemDecoration);

        adapter = new SnsAdapter(getContext(), statusList, viewModel);
        binding.setAdapter(adapter);

        binding.snsList.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager, adapter) {
            @Override
            public void onLoadMore(int current_page) {

                viewModel.loadMoreStatus();

            }
        });

        viewModel.loadTimeLine();

    }


}
