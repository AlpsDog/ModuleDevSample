package com.wonly.lib_base.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @Description: 为业务处理层，既能调用UI逻辑，又能请求数据，该层为纯Java类
 */
public interface IBasePresenter<V extends IBaseView> extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onAttachView(V view);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDetachView();

}
