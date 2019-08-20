package com.wonly.lib_base.base;

import android.app.Application;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_base.base
 * @Author: HSL
 * @Time: 2019/08/20 15:01
 * @E-mail: xxx@163.com
 * @Description: 用户模块在Application中的初始化
 */
public interface IInitModuleInApplication {

    /**
     * 初始化模块任务
     *
     * @param application 上下文
     */
    void initModule(Application application);
}
