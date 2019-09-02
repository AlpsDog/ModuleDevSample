package com.wonly.lib_base.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.base
 * @Author: HSL
 * @Time: 2019/08/22 16:43
 * @E-mail: xxx@163.com
 * @Description: 基类Fragment~
 */
public abstract class BaseFragment<V extends ViewDataBinding> extends RxFragment {

    protected final String TAG = this.getClass().getSimpleName();
    protected V mBinding;
    protected View mRootView;
    protected Activity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initExtra(getArguments());
        } else {
            initExtra(new Bundle());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mBinding = DataBindingUtil.inflate(inflater, onLayoutResID(savedInstanceState), container, false);
            mRootView = mBinding.getRoot();
            if (useEventBus()) EventBus.getDefault().register(this);
        }
        //View状态会保存，移除，避免不必要麻烦
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMvp(savedInstanceState);
        initView(savedInstanceState);
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) EventBus.getDefault().unregister(this);
        mRootView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    /**
     * 加载布局
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int onLayoutResID(@Nullable Bundle savedInstanceState);

    /**
     * 获取Activity传递数据
     *
     * @param bundle
     */
    protected void initExtra(Bundle bundle) {
        //需要获取Activity传递给自己的数据时，进行重写
    }

    /**
     * 使用MVPFragment时，重写
     *
     * @param savedInstanceState
     */
    protected void initMvp(@Nullable Bundle savedInstanceState) {
        //
    }

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 加载数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 设置监听事件
     */
    protected void initListener() {
        //需要设置监听事件时，进行重写
    }

    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

}
