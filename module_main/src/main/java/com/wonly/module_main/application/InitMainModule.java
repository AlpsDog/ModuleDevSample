package com.wonly.module_main.application;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.base.IInitModuleInApplication;
import com.wonly.lib_base.utils.ModuleManagerUtils;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.module_main.application
 * @Author: HSL
 * @Time: 2019/08/20 15:48
 * @E-mail: xxx@163.com
 * @Description: 在Application中反射此类 {@link ModuleManagerUtils}
 */
public class InitMainModule implements IInitModuleInApplication {

    /**
     * 在Application中回调
     *
     * @param application 上下文
     */
    @Override
    public void initModule(Application application) {
        Logger.d("InitMainModule: " + application);
    }
}
