package com.wonly.lib_base.download;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HSL
 * @Time: 2019/9/5 14:37
 * @E-mail: xxx@163.com
 * @Description: 用于只暴露success和error
 */
public abstract class BaseDownloadObserver<T> implements Observer<T> {
    @Override
    public abstract void onSubscribe(Disposable d);

    @Override
    public void onNext(T t) {
        onDownloadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onDownloadError(e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onDownloadSuccess(T t);

    protected abstract void onDownloadError(Throwable e);
}
