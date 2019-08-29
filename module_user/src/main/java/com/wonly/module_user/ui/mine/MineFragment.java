package com.wonly.module_user.ui.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wonly.lib_base.base.BaseMVPFragment;
import com.wonly.lib_common.event.MainEvent;
import com.wonly.lib_common.router.UserPath;
import com.wonly.lib_common.service.IHomeModuleService;
import com.wonly.module_user.R;
import com.wonly.module_user.databinding.UserFragmentMineBinding;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author: HSL
 * @Time: 2019/8/20 17:42
 * @E-mail: xxx@163.com
 * @Description: 我的~
 */
@Route(path = UserPath.FRAG_USER_HOME)
public class MineFragment extends BaseMVPFragment<UserFragmentMineBinding, MinePresenter> {

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.user_fragment_mine;
    }

    @Override
    protected MinePresenter onCreatePresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        mBinding.testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainEvent<String> mainEvent = new MainEvent<>();
                mainEvent.setData("来自用户模块的Event");
                EventBus.getDefault().post(mainEvent);

                IHomeModuleService moduleService = ARouter.getInstance().navigation(IHomeModuleService.class);
                if (moduleService != null) {
                    moduleService.refreshHomeFragment("来自用户刷新!");
                }
            }
        });
    }
}
