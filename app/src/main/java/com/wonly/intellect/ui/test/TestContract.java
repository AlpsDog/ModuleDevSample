package com.wonly.intellect.ui.test;

import com.wonly.intellect.bean.WeatherInfo;
import com.wonly.lib_base.mvp.IBasePresenter;
import com.wonly.lib_base.mvp.IBaseView;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.intellect.ui.test
 * @Author: HSL
 * @Time: 2019/09/02 15:53
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public interface TestContract {

    interface ITestView extends IBaseView {
        // TODO: 2019/9/2
        void loginSuccess();
    }

    interface ITestPresenter extends IBasePresenter<ITestView> {
        /**
         * 登录
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
