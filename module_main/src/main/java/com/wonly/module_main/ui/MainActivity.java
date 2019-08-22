package com.wonly.module_main.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonly.lib_base.base.BaseActivity;
import com.wonly.lib_common.router.MainPath;
import com.wonly.module_main.R;
import com.wonly.module_main.databinding.MainActivityMainBinding;

/**
 * @Author: HSL
 * @Time: 2019/8/21 17:35
 * @E-mail: xxx@163.com
 * @Description: 应用主页~
 */
@Route(path = MainPath.ACT_MAIN_MAIN)
public class MainActivity extends BaseActivity {

    private MainActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.main_activity_main);
        mMainBinding.mainTab.initFragment(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMainBinding.mainTab.saveCurrentTabIndex();
    }
}
