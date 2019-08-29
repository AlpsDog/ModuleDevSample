package com.wonly.lib_base.base;

/**
 * @Author: HSL
 * @Time: 2019/8/27 15:40
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class BaseEvent<T> {
    /**
     * 页面Class
     */
    private Class<?> pageClass = null;
    /**
     * 数据
     */
    private T data = null;


    public Class<?> getPageClass() {
        return pageClass;
    }

    public void setPageClass(Class<?> pageClass) {
        this.pageClass = pageClass;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
