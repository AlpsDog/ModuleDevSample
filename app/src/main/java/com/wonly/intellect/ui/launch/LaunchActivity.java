package com.wonly.intellect.ui.launch;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityLaunchBinding;
import com.wonly.intellect.ui.main.MainActivity;
import com.wonly.lib_base.base.BaseActivity;

/**
 * @Author: HSL
 * @Time: 2019/8/21 14:38
 * @E-mail: xxx@163.com
 * @Description: 启动页~
 */
public class LaunchActivity extends BaseActivity<ActivityLaunchBinding> {

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(mActivity);
            }
        }, 2000);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
