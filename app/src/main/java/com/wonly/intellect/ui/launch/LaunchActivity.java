package com.wonly.intellect.ui.launch;


import android.os.Bundle;
import android.os.Handler;

import com.wonly.intellect.R;
import com.wonly.intellect.ui.main.MainActivity;
import com.wonly.lib_base.base.BaseActivity;

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
            MainActivity.start(LaunchActivity.this);
        }, 3000);
    }
}
