package com.wonly.lib_base.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.base
 * @Author: HSL
 * @Time: 2019/08/22 16:42
 * @E-mail: xxx@163.com
 * @Description: 基类Activity~
 */
public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V mBinding;
    protected AppCompatActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mBinding = DataBindingUtil.setContentView(this, onLayoutResID(savedInstanceState));
        if (useEventBus()) EventBus.getDefault().register(this);
        initExtra(savedInstanceState);
        initMvp(savedInstanceState);
        initView(savedInstanceState);
        initData(savedInstanceState);
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) EventBus.getDefault().unregister(this);
    }

    /**
     * 布局ID
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int onLayoutResID(@Nullable Bundle savedInstanceState);

    /**
     * 初始化MVP
     * @param savedInstanceState
     */
    protected void initMvp(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 获取页面传递数据
     *
     * @param savedInstanceState
     */
    protected void initExtra(@Nullable Bundle savedInstanceState) {
        //需要获取Activity传递给自己的数据时，进行重写
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
     * 使用EventBus
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

}
