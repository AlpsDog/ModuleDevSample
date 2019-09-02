package com.wonly.intellect.ui.test;

import com.wonly.intellect.ui.test.net.TestNet;
import com.wonly.lib_base.base.BaseBean;
import com.wonly.lib_base.mvp.IBasePresenterImpl;
import com.wonly.lib_base.rx.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.intellect.ui.test
 * @Author: HSL
 * @Time: 2019/09/02 15:54
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class TestPresenter extends IBasePresenterImpl<TestContract.ITestView> implements TestContract.ITestPresenter {

    @Override
    public void login(String username, String password) {
        TestNet.getTestService().login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getIView().<BaseBean>bindToLife())
                .subscribe(new BaseObserver<BaseBean>(getIView()) {

                    @Override
                    protected void onSuccess(BaseBean baseBean) {
                        getIView().loginSuccess();
                    }
                });
    }
}
