package com.wonly.lib_base.application;

import com.wonly.lib_base.utils.ModuleManagerUtils;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.application
 * @Author: HSL
 * @Time: 2019/08/20 15:38
 * @E-mail: xxx@163.com
 * @Description: 模块Application~
 */
public class ModuleApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化模块资源
        ModuleManagerUtils.getInstance().initModule(this);
    }
}
