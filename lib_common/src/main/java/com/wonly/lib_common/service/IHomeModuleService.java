package com.wonly.lib_common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_common.service
 * @Author: HSL
 * @Time: 2019/08/27 17:05
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public interface IHomeModuleService extends IProvider {

    /**
     * 刷新
     *
     * @param param
     */
    void refreshHomeFragment(String param);

    /**
     * 设置home文本
     *
     * @param text
     */
    void setHomeText(String text);
}
