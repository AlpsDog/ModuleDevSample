package com.wonly.intellect.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityMainBinding;
import com.wonly.intellect.ui.test.TestActivity;
import com.wonly.lib_base.mvp.BaseMVPActivity;
import com.wonly.lib_common.event.MainEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author: HSL
 * @Time: 2019/8/21 17:35
 * @E-mail: xxx@163.com
 * @Description: 应用主页~
 */
public class MainActivity extends BaseMVPActivity<ActivityMainBinding, MainPresenter> {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter();
    }

    /**
     * 使用EventBus
     *
     * @return
     */
    @Override
    protected boolean useEventBus() {
        return true;
    }

    /**
     * EventBus消息处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainTabEvent(MainEvent event) {
        Toast.makeText(mActivity, "App模块弹出：" + event, Toast.LENGTH_SHORT).show();
    }

    /**
     * 主页Event，处理粘性事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mainStickyEvent(MainEvent event) {
        // TODO: 2019/8/27  
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinding.mainTab.initFragment(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        TestActivity.start(mActivity);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBinding.mainTab.saveCurrentTabIndex();
    }
}
