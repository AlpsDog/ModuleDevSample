package com.wonly.lib_base.rx;

import com.wonly.lib_base.rx.scheduler.IoMainScheduler;

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

}
