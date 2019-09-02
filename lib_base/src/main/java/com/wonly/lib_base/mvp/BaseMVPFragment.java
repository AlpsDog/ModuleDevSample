package com.wonly.lib_base.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.wonly.lib_base.base.BaseActivity;
import com.wonly.lib_base.base.BaseFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/27 8:38
 * @E-mail: xxx@163.com
 * @Description: 纯粹的 MVP 包装，不要增加任何View层基础功能
 * 如果要添加基类功能，请在{@link BaseActivity} 中添加
 */
public abstract class BaseMVPFragment<V extends ViewDataBinding, P extends IBasePresenter> extends BaseFragment<V> implements IBaseView {

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
    public void onDestroyView() {
        super.onDestroyView();
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }

}
