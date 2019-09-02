package com.wonly.lib_base.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.wonly.lib_base.base.BaseActivity;
import com.wonly.lib_base.utils.ToastUtils;

/**
 * @Author: HSL
 * @Time: 2019/8/27 8:37
 * @E-mail: xxx@163.com
 * @Description: 纯粹的 MVP 包装，不要增加任何View层基础功能
 * 如果要添加基类功能，请在{@link BaseActivity} 中添加
 */
public abstract class BaseMVPActivity<V extends ViewDataBinding, P extends IBasePresenter> extends BaseActivity<V> implements IBaseView {

    protected P mPresenter;

    @Override
    protected void initMvp(@Nullable Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }
        mPresenter.onAttachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachView();
    }

    /**
     * 抽象方法
     * 创建Presenter
     *
     * @return
     */
    protected abstract P onCreatePresenter();


    @Override
    public void showLoading() {
        Logger.t("net").d("Loading开始");
    }

    @Override
    public void hideLoading() {
        Logger.t("net").d("Loading结束");
    }

    @Override
    public void showMsg(String msg) {
        Logger.t("net").d("msg:" + msg);
        ToastUtils.showShort(msg);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

}
