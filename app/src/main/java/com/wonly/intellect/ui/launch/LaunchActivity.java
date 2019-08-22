package com.wonly.intellect.ui.launch;


import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wonly.intellect.R;
import com.wonly.lib_base.base.BaseActivity;
import com.wonly.lib_common.router.MainPath;

/**
 * @Author: HSL
 * @Time: 2019/8/21 14:38
 * @E-mail: xxx@163.com
 * @Description: 启动页~
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed(() -> {
            ARouter.getInstance().build(MainPath.ACT_MAIN_MAIN).navigation();
        }, 3000);
    }
}
