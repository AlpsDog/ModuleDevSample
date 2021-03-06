package com.wonly.module_home.application;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.base.IInitModuleInApplication;
import com.wonly.lib_base.utils.ModuleManagerUtils;

/**
 * @Author: HSL
 * @Time: 2019/8/20 15:58
 * @E-mail: xxx@163.com
 * @Description: 在Application中反射此类 {@link ModuleManagerUtils}~
 */
public class InitHomeModule implements IInitModuleInApplication {

    /**
     * 在Application中回调
     *
     * @param application 上下文
     */
    @Override
    public void initModule(Application application) {
        Logger.d("InitHomeModule: " + application);
    }
}
