package com.wonly.lib_base.base;

import java.io.Serializable;

/**
 * @Author: HSL
 * @Time: 2019/9/2 11:12
 * @E-mail: xxx@163.com
 * @Description: BaseBean
 */
public class BaseBean<T> implements Serializable {

    private int errorCode;
    private String errorMsg;
    private T data;

    public BaseBean() {
    }

    public BaseBean(int errorCode, String errorMsg, T data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
