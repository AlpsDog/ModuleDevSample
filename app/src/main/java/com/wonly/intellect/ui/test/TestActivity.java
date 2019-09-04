package com.wonly.intellect.ui.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityTestBinding;
import com.wonly.lib_base.mvp.BaseMVPActivity;
import com.wonly.lib_common.widget.DefaultDialog;

/**
 * @Author: HSL
 * @Time: 2019/9/2 15:45
 * @E-mail: xxx@163.com
 * @Description: 测试界面
 */
public class TestActivity extends BaseMVPActivity<ActivityTestBinding, TestPresenter> implements TestContract.ITestView {

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter onCreatePresenter() {
        return new TestPresenter();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    public void onNetRequestClick(View view) {
        mPresenter.login("13967189624", "123456");
    }

    public void onNetCacheClick(View view) {
        //loading
//        LoadingDialog dialog = new LoadingDialog(this);
//        dialog.showDialog();
        DefaultDialog dialog = new DefaultDialog(this);
        dialog.setTitleText("测试标题");
        dialog.setContentText("fdajferjkeofkepwrijfjdklfmdl;fkje0rie4");
        dialog.show();
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {

    }
}
