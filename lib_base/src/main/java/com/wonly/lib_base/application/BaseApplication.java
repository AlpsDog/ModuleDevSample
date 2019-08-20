package com.wonly.lib_base.application;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * @Author: HSL
 * @Time: 2019/8/20 14:32
 * @E-mail: xxx@163.com
 * @Description: 基类Application~
 */
public abstract class BaseApplication extends RegisterActivityLifeContext {

    private static Application instance;

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 禁止跟随系统字体大小调节
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration configuration = res.getConfiguration();
        if (configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }
        return res;
    }
}
