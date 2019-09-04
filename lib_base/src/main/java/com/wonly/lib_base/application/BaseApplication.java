package com.wonly.lib_base.application;

import android.content.res.Configuration;
import android.content.res.Resources;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wonly.lib_base.R;
import com.wonly.lib_base.base.CatalogParam;
import com.wonly.lib_base.utils.CatalogUtils;

/**
 * @Author: HSL
 * @Time: 2019/8/20 14:32
 * @E-mail: xxx@163.com
 * @Description: 基类Application~
 */
public abstract class BaseApplication extends RegisterActivityLifeContext {

    private static BaseApplication instance;

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    //防止内存泄露
    static {
        ////目录配置
        ////目录配置
        CatalogUtils.setOnCatalogInitListener(() -> {
            CatalogParam catalogParam = new CatalogParam();
            catalogParam.setAppDir("wonly");
            return catalogParam;
        });
        ////刷新控件配置
        ////刷新控件配置
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableLoadMore(false);
            layout.setEnableAutoLoadMore(false);
            layout.setEnableOverScrollDrag(false);
            layout.setEnableOverScrollBounce(true);
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.white, R.color.color_333333);
        });
        //设置全局头部
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            ClassicsHeader classicsHeader = new ClassicsHeader(context);
            return classicsHeader;
        });
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
