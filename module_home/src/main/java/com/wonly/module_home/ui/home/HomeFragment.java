package com.wonly.module_home.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonly.lib_base.mvp.BaseMVPFragment;
import com.wonly.lib_common.router.HomePath;
import com.wonly.module_home.R;
import com.wonly.module_home.databinding.HomeFragmentHomeBinding;
import com.wonly.module_home.services.HomeServiceImp;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:31
 * @E-mail: xxx@163.com
 * @Description: 首页~
 */
@Route(path = HomePath.FRAG_HOME_HOME)
public class HomeFragment extends BaseMVPFragment<HomeFragmentHomeBinding, HomePresenter> {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.home_fragment_home;
    }

    @Override
    protected HomePresenter onCreatePresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        HomeServiceImp.getInstance().setCallBack(new HomeServiceImp.ModuleCallBack() {
            @Override
            public void refreshHomeFragment(String param) {
                mBinding.testTv.setText(param);
                Toast.makeText(mActivity, "调用首页模块：" + param, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
