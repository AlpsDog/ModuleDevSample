package com.wonly.lib_base.application;

import android.app.Application;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.wonly.lib_base.BuildConfig;
import com.wonly.lib_base.base.IInitModuleInApplication;
import com.wonly.lib_base.utils.ModuleManagerUtils;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.application
 * @Author: HSL
 * @Time: 2019/08/20 15:25
 * @E-mail: xxx@163.com
 * @Description: 在Application中反射此类 {@link ModuleManagerUtils}
 */
public class InitBaseModule implements IInitModuleInApplication {

    /**
     * 在Application中回调
     *
     * @param application 上下文
     */
    @Override
    public void initModule(Application application) {
        Logger.d("InitBaseModule：" + application);
        initARouter(application);
        initLogger();
    }

    /**
     * 初始化阿里ARouter
     *
     * @param application
     */
    private void initARouter(Application application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(application);
    }

    /**
     * 初始化Logger
     */
    private void initLogger() {
        PrettyFormatStrategy.Builder newBuilder = PrettyFormatStrategy.newBuilder();
        //（可选）是否显示线程信息。 默认值为true
        newBuilder.showThreadInfo(true);
        //（可选）要显示的方法行数。 默认2
        newBuilder.methodCount(2);
        //（可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
        newBuilder.methodOffset(0);
        //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
        newBuilder.tag("log-wl");
        FormatStrategy formatStrategy = newBuilder.build();
        //修改全局的TAG
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        //控制打印开关
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
        //保存log到文件 /storage/emulated/0
        Logger.addLogAdapter(new DiskLogAdapter());
    }
}
