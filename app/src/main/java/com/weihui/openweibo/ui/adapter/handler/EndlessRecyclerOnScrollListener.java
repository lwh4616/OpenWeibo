package com.weihui.openweibo.ui.adapter.handler;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.weihui.openweibo.ui.base.BaseRecyclerViewAdapter;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load

    private boolean loading = false; // True if we are still waiting for the last set of data to load.

    private int visibleThreshold = 1; // The minimum amount of items to have below your current scroll position before loading more.

    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    private BaseRecyclerViewAdapter adapter;

    private Handler handler = new Handler(Looper.getMainLooper());

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, BaseRecyclerViewAdapter adapter) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.adapter = adapter;
        adapter.setListener(this);


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy <= 0) {
            loading = false;
            return;
        }
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();


//        if (loading) {
//            if (totalItemCount > previousTotal) {
//                loading = false;
//                previousTotal = totalItemCount;
//            }
//        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {

            // End has been reached
            // Do something
            current_page++;
            loading = true;

            handler.post(new Runnable() {
                @Override
                public void run() {

                    Log.e(TAG, "dy :" + dy);
                    if (adapter.startLoadMore()) {
                        Log.d(TAG, "onLoadMore ");
                        onLoadMore(current_page);
                    }
                }
            });

        }
    }

    public void setLoading(final boolean loading) {
        Log.d(TAG, "setLoading " + loading);
        this.loading = loading;
    }

    public abstract void onLoadMore(int current_page);

}

