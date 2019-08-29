package com.wonly.lib_base.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * @Author: HSL
 * @Time: 2019/8/27 8:37
 * @E-mail: xxx@163.com
 * @Description: 纯粹的 MVP 包装，不要增加任何View层基础功能
 * 如果要添加基类功能，请在{@link BaseActivity} 中添加
 */
public abstract class BaseMVPActivity<V extends ViewDataBinding, P extends IBasePresenter>
        extends BaseActivity<V> implements IBaseView {

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

}
