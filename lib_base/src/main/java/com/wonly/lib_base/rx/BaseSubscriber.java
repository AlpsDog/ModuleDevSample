package com.wonly.lib_base.rx;

import android.annotation.SuppressLint;

import com.wonly.lib_base.base.BaseBean;
import com.wonly.lib_base.mvp.IBaseView;
import com.wonly.lib_base.net.HttpStatus;
import com.wonly.lib_base.net.exception.ExceptionHandle;
import com.wonly.lib_base.utils.NetworkUtils;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @Author: HSL
 * @Time: 2019/9/2 11:11
 * @E-mail: xxx@163.com
 * @Description: BaseSubscriber 背压使用
 */
public abstract class BaseSubscriber<T extends BaseBean> extends ResourceSubscriber<T> {

    private IBaseView mView;
    private String mErrorMsg = "";
    private boolean bShowLoading = true;

    public BaseSubscriber(IBaseView view) {
        this.mView = view;
    }

    public BaseSubscriber(IBaseView view, boolean bShowLoading) {
        this.mView = view;
        this.bShowLoading = bShowLoading;
    }

    /**
     * 成功的回调
     */
    protected abstract void onSuccess(T t);

    /**
     * 错误的回调
     */
    protected void onError(T t) {
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onStart() {
        super.onStart();
        if (bShowLoading) mView.showLoading();
        if (!NetworkUtils.isAvailableByPing()) {
            mView.showMsg("当前网络不可用，请检查网络设置");
            onComplete();
        }
    }

    @Override
    public void onNext(T t) {
        mView.hideLoading();
        if (t.getErrorCode() == HttpStatus.SUCCESS) {
            onSuccess(t);
        } else if (t.getErrorCode() == HttpStatus.TOKEN_INVALID) {
            // TODO 处理 token 过期
        } else {
            onError(t);
            if (!t.getErrorMsg().isEmpty()) {
                mView.showMsg(t.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.hideLoading();
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
        mView.hideLoading();
    }
}
