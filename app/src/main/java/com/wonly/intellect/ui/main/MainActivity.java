package com.wonly.intellect.ui.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityMainBinding;
import com.wonly.lib_base.base.BaseActivity;

/**
 * @Author: HSL
 * @Time: 2019/8/21 17:35
 * @E-mail: xxx@163.com
 * @Description: 应用主页~
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding mMainBinding;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.mainTab.initFragment(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMainBinding.mainTab.saveCurrentTabIndex();
    }
}
