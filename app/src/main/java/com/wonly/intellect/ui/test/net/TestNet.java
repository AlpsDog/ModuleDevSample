package com.wonly.intellect.ui.test.net;

import com.wonly.lib_base.net.RetrofitManager;
import com.wonly.lib_common.net.BaseUrl;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.intellect.ui.test.net
 * @Author: HSL
 * @Time: 2019/09/02 15:58
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class TestNet {

    /**
     * 获取 RetrofitService
     */
    public static TestService getTestService() {
        return RetrofitManager.getInstance().obtainRetrofitService(BaseUrl.WRONLY, TestService.class);
    }

}
