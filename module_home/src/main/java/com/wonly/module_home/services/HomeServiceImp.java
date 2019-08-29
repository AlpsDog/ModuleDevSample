package com.wonly.module_home.services;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wonly.lib_common.router.HomePath;
import com.wonly.lib_common.service.IHomeModuleService;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.module_home.ui.home
 * @Author: HSL
 * @Time: 2019/08/27 17:31
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
@Route(path = HomePath.HOME_MODULE_SERVICE)
public class HomeServiceImp implements IHomeModuleService {

    private ModuleCallBack mCallBack;
    private Context mContext;

    @Override
    public void refreshHomeFragment(String param) {
        if (mCallBack != null) mCallBack.refreshHomeFragment(param);
    }

    @Override
    public void setHomeText(String text) {
        if (mCallBack != null) mCallBack.refreshHomeFragment(text);
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }

    public static class ModuleCallBack {

        public void refreshHomeFragment(String param) {
            // TODO: 2019/8/27
        }

        public void setHomeText(String text) {

        }
    }

    public void setCallBack(ModuleCallBack callBack) {
        mCallBack = callBack;
    }

    public static HomeServiceImp getInstance() {
        return (HomeServiceImp) ARouter.getInstance().navigation(IHomeModuleService.class);
    }

}
