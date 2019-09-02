package com.wonly.lib_base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @Description: 负责处理 UI
 */
public interface IBaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     *
     * @param msg
     */
    void showMsg(String msg);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
