package com.wonly.lib_common.event;

import com.wonly.lib_base.base.BaseEvent;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_common.event
 * @Author: HSL
 * @Time: 2019/08/27 11:52
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class MainEvent<T> extends BaseEvent<T> {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
