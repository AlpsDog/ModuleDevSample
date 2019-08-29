package com.wonly.module_user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wonly.lib_base.base.BaseActivity;
import com.wonly.module_user.R;
import com.wonly.module_user.databinding.UserActivityMineBinding;
import com.wonly.module_user.ui.mine.MineFragment;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:43
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class MineActivity extends BaseActivity<UserActivityMineBinding> {

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.user_activity_mine;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.mine_container_fl, MineFragment.newInstance()).commit();

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

}
