package com.wonly.lib_base.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author: HSL
 * @Time: 2019/4/23 17:06
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class BindingViewHolder<BT extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private BT mBinding;
    private int viewType = 0;

    public BindingViewHolder(BT binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public BindingViewHolder(View view) {
        super(view);
    }

    public BT getBinding() {
        return mBinding;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
