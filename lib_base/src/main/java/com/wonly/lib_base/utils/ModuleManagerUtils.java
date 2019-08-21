package com.wonly.lib_base.utils;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.base.IInitModuleInApplication;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.utils
 * @Author: HSL
 * @Time: 2019/08/20 15:08
 * @E-mail: xxx@163.com
 * @Description: 模块管理工具里~
 */
public class ModuleManagerUtils {

    private static final String[] mModuleName = {
            "com.wonly.lib_base.application.InitBaseModule",
            "com.wonly.module_home.application.InitHomeModule",
            "com.wonly.module_main.application.InitMainModule",
            "com.wonly.module_user.application.InitUserModule"
    };

    /**
     * 内部类，在装载该内部类时才会去创建单例对象
     */
    private static class SingletonHolder {
        public static ModuleManagerUtils instance = new ModuleManagerUtils();
    }

    public static ModuleManagerUtils getInstance() {
        return SingletonHolder.instance;
    }

    private ModuleManagerUtils() {
    }

    /**
     * 反射
     * <p>
     * 用于在主工程Application中初始化
     * 每个模块中需要初始化的任务
     *
     * @param application
     */
    public void initModule(Application application) {
        for (String moduleInitName : mModuleName) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IInitModuleInApplication newInstance = (IInitModuleInApplication) clazz.newInstance();
                newInstance.initModule(application);
            } catch (ClassNotFoundException e) {
                Logger.e(e.getMessage());
            } catch (InstantiationException e) {
                Logger.e(e.getMessage());
            } catch (IllegalAccessException e) {
                Logger.e(e.getMessage());
            }
        }
    }

}
