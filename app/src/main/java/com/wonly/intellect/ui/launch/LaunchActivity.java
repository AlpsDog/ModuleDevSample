package com.wonly.intellect.ui.launch;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityLaunchBinding;
import com.wonly.intellect.ui.test.TestActivity;
import com.wonly.lib_base.mvp.BaseMVPActivity;

/**
 * @Author: HSL
 * @Time: 2019/8/21 14:38
 * @E-mail: xxx@163.com
 * @Description: 启动页~
 */
public class LaunchActivity extends BaseMVPActivity<ActivityLaunchBinding, LaunchPresenter> {

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_launch;
    }

    @Override
    protected LaunchPresenter onCreatePresenter() {
        return new LaunchPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TestActivity.start(mActivity);
            }
        }, 2000);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

}
