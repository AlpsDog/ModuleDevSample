package com.wonly.intellect.application;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_base.utils.ModuleManagerUtils;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.intellect.application
 * @Author: HSL
 * @Time: 2019/08/21 14:27
 * @E-mail: xxx@163.com
 * @Description: 主工程Application
 */
public class AppContext extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("主工程Application");
        ModuleManagerUtils.getInstance().initModule(this);
    }
}
