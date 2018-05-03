package com.weihui.openweibo.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weihui.openweibo.R;
import com.weihui.openweibo.ui.adapter.handler.EndlessRecyclerOnScrollListener;
import com.weihui.openweibo.entity.LoadMoreBean;
import com.weihui.openweibo.databinding.LoadMoreBinding;

import java.util.List;

/**
 * Created by weihui on 2018/4/9.
 */

public abstract class BaseRecyclerViewAdapter<T extends ViewDataBinding, D, M>  extends RecyclerView.Adapter {

    protected List<D> datas;

    protected Context mContext;

    protected M viewModel;

    EndlessRecyclerOnScrollListener listener;


    private final int ITEM_TYPE_LOAD = -1;


    private String loadMsg = "Loading...";

    private boolean isLoadError = false;

    public interface  LoadHandler
    {

        void  onClickLoad(View view);

    }




    public BaseRecyclerViewAdapter(Context mContext, List<D> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    public void setListener(EndlessRecyclerOnScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    public boolean startLoadMore() {
        if (!isLoadType()) {
            datas.add(null);
            notifyItemInserted(datas.size() - 1);
            return true;
        }

        return false;
    }


    protected boolean isLoadType(int position) {
        if (null == datas.get(position) || (datas.get(position) instanceof LoadMoreBean)) {
            return true;
        }

        return false;
    }

    protected boolean isLoadType() {
        int position = datas.size() - 1;
        return isLoadType(position);
    }

    public void updateLoadMsg(String msg, boolean error) {
        loadMsg = msg;
        isLoadError = error;
        notifyItemChanged(datas.size() - 1);

    }

    public void loadDone() {

        if (isLoadType()) {
            datas.remove(datas.size() - 1);
            notifyItemRemoved(datas.size());
        }
        if (null != listener) {
            listener.setLoading(false);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isLoadType(position)) {
            return ITEM_TYPE_LOAD;
        }
        return super.getItemViewType(position);
    }

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    protected  void onClickLoad(View view){

    }

    @Override
    public final BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD) {

            FootViewHolder footViewHolder = new FootViewHolder(parent, R.layout.load_more);

            LoadMoreBinding binding = (LoadMoreBinding) (footViewHolder.getBinding());
            binding.setHandler(new LoadHandler() {
                @Override
                public void onClickLoad(View view) {
                    BaseRecyclerViewAdapter.this.onClickLoad(view);
                }
            });
            return footViewHolder;

        }
        return makeViewHolder(parent, viewType);

    }

    public BaseRecyclerViewHolder makeViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(parent, getLayoutResId(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (ITEM_TYPE_LOAD != getItemViewType(position)) {
            onBindViewHolder(((BaseRecyclerViewHolder) holder).binding, datas.get(position), holder, position);
        } else {
            LoadMoreBinding binding = (LoadMoreBinding) (((FootViewHolder) holder).getBinding());
            binding.loadingMsg.setText(loadMsg);

            binding.loadingProgress.setVisibility(isLoadError ? View.GONE : View.VISIBLE);

        }

    }


    public abstract void onBindViewHolder(T binding, D itemData, RecyclerView.ViewHolder holder, int position);


    class FootViewHolder extends BaseRecyclerViewHolder {


        FootViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }
    }


    /**
     * adapter中的ViewHolder类，主要作用为构建item与其binding对象
     */
   public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

        private T binding;


        public T getBinding() {
            return binding;
        }

        BaseRecyclerViewHolder(ViewGroup viewGroup, int layoutId) {
            super(DataBindingUtil.inflate(LayoutInflater.from(
                    viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
            binding = DataBindingUtil.getBinding(this.itemView);
        }
    }

}
