package com.wonly.lib_base.rx;


import android.annotation.SuppressLint;
import android.util.Log;

import com.wonly.lib_base.base.BaseBean;
import com.wonly.lib_base.mvp.IBaseView;
import com.wonly.lib_base.net.HttpStatus;
import com.wonly.lib_base.net.exception.ExceptionHandle;
import com.wonly.lib_base.utils.NetworkUtils;

import io.reactivex.observers.ResourceObserver;

/**
 * @Author: HSL
 * @Time: 2019/9/2 11:10
 * @E-mail: xxx@163.com
 * @Description: BaseObserver
 */
public abstract class BaseObserver<T extends BaseBean> extends ResourceObserver<T> {

    private IBaseView mView;
    private String mErrorMsg = "";
    private boolean bShowLoading = true;

    public BaseObserver(IBaseView view) {
        this.mView = view;
    }

    public BaseObserver(IBaseView view, boolean bShowLoading) {
        this.mView = view;
        this.bShowLoading = bShowLoading;
    }

    /**
     * 成功的回调
     */
    protected abstract void onSuccess(T t);

    /**
     * 失败的回调
     */
    protected void onFail(T t) {
        //根据需要重写
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
        if (bShowLoading) mView.showLoading();
        //网络不可用时，直接阻断
        if (!NetworkUtils.isConnected()) {
            mView.showMsg("当前网络不可用，请检查网络设置");
            if (!isDisposed()) dispose();
            if (bShowLoading) mView.hideLoading();
        }
    }

    @Override
    public void onNext(T t) {
        if (bShowLoading) mView.hideLoading();
        if (t.getErrorCode() == HttpStatus.SUCCESS) {
            onSuccess(t);
        } else if (t.getErrorCode() == HttpStatus.TOKEN_INVALID) {
            // TODO 处理 token 过期
        } else {
            onFail(t);
            if (!t.getErrorMsg().isEmpty()) {
                mView.showMsg(t.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (bShowLoading) mView.hideLoading();
        if (mView == null) {
            throw new RuntimeException("mView can not be null");
        }
        if (mErrorMsg.isEmpty()) {
            mErrorMsg = ExceptionHandle.handleException(e);
        }
        mView.showMsg(mErrorMsg);
    }

    @Override
    public void onComplete() {
        if (bShowLoading) mView.hideLoading();
    }
}
