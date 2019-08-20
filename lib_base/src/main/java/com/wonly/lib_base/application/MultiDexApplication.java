package com.wonly.lib_base.application;

import android.app.Application;
import android.content.Context;

/**
 * @Author: HSL
 * @Time: 2019/4/15 9:24
 * @E-mail: xxx@163.com
 * @Description: 进行分包解决“65535方法数超标”
 */
public class MultiDexApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // TODO: 2019/8/20 初始化 MultiDex
    }
}
