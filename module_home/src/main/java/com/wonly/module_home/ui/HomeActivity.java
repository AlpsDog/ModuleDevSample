package com.wonly.module_home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wonly.lib_base.base.BaseActivity;
import com.wonly.module_home.R;
import com.wonly.module_home.databinding.HomeActivityHomeBinding;
import com.wonly.module_home.ui.home.HomeFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:23
 * @E-mail: xxx@163.com
 * @Description: 独立模块时，启动界面~
 */
public class HomeActivity extends BaseActivity<HomeActivityHomeBinding> {


    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.home_activity_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.home_container_fl, HomeFragment.newInstance()).commit();
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }
}
