package com.wonly.lib_base.base;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.utils.ObjectUtils;

import java.lang.ref.WeakReference;

/**
 * @Author: HSL
 * @Time: 2019/8/26 17:46
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class IBasePresenterImpl<V extends IBaseView> implements IBasePresenter<V> {

    private WeakReference<V> mViewRef;

    /**
     * 绑定view，
     * 一般在初始化中调用该方法
     *
     * @param view
     */
    @Override
    public void onAttachView(V view) {
        mViewRef = new WeakReference<V>(view);
        Logger.t("mvp").d("MVP_ATTACH: view绑定完毕,view类型：" + view);
    }

    /**
     * 断开view
     * 一般在onDestroy中调用
     */
    @Override
    public void onDetachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
            Logger.t("mvp").d("MVP_DETACH: view解绑完毕o");
        }
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     *
     * @return
     */
    protected boolean isAttached() {
        return !ObjectUtils.isEmpty(mViewRef) && !ObjectUtils.isEmpty(mViewRef.get());
    }

    /**
     * 获取连接的view
     * 即：IBaseView的具体实现
     *
     * @return
     */
    protected V getIView() {
        return mViewRef.get();
    }

}
