package com.wonly.lib_base.rx.scheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: HSL
 * @Time: 2019/9/2 11:10
 * @E-mail: xxx@163.com
 * @Description: IoMainScheduler
 */
public class IoMainScheduler extends BaseScheduler {
    public IoMainScheduler() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
