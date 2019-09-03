package com.wonly.lib_base.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.wonly.lib_base.mvp.BaseMVPFragment;
import com.wonly.lib_base.mvp.IBasePresenter;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:41
 * @E-mail: xxx@163.com
 * @Description: Fragment懒加载
 */
public abstract class BaseLazyFragment<V extends ViewDataBinding, P extends IBasePresenter> extends BaseMVPFragment<V, P> {

    /**
     * Fragment 处理懒加载，为了防止 setUserVisibleHint 进入多次导致数据重复加载
     */
    protected boolean isUIVisible = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !isUIVisible) {
            isUIVisible = true;
            lazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !isUIVisible) {
            isUIVisible = true;
            lazyLoad();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isUIVisible = false;
    }

    @UiThread
    protected abstract void lazyLoad();

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        //无需重写
    }
}
