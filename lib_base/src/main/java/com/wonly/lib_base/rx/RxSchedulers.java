package com.wonly.lib_base.rx;

import com.wonly.lib_base.net.function.RetryWithDelay;
import com.wonly.lib_base.rx.scheduler.IoMainScheduler;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: HSL
 * @Time: 2019/9/2 11:11
 * @E-mail: xxx@163.com
 * @Description: 统一线程处理类
 */
public class RxSchedulers {

    public static IoMainScheduler ioToMain() {
        return new IoMainScheduler();
    }

    public static <T> ObservableTransformer<T, T> applyScheduler() {
        return upstream -> upstream
                .retryWhen(new RetryWithDelay(2000))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
