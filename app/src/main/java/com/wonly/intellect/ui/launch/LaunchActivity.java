package com.wonly.intellect.ui.launch;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tbruyelle.rxpermissions2.Permission;
import com.wonly.intellect.R;
import com.wonly.intellect.databinding.ActivityLaunchBinding;
import com.wonly.intellect.ui.main.MainActivity;
import com.wonly.intellect.ui.test.TestActivity;
import com.wonly.lib_base.mvp.BaseMVPActivity;
import com.wonly.lib_base.permission.PermissionUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: HSL
 * @Time: 2019/8/21 14:38
 * @E-mail: xxx@163.com
 * @Description: 启动页~
 */
public class LaunchActivity extends BaseMVPActivity<ActivityLaunchBinding, LaunchPresenter> {

    private String[] mNeedPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected int onLayoutResID(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_launch;
    }

    @Override
    protected LaunchPresenter onCreatePresenter() {
        return new LaunchPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPermissionUtils.initRxPermission(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        requestPermission();
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        mPermissionUtils.requestPermission(mNeedPermission[0]);
    }

    /**
     * 下一步
     */
    private void startNextStep() {
        Disposable disposable = Observable.timer(4, TimeUnit.SECONDS)
                .compose(bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        MainActivity.start(mActivity);
                        finish();
                    }
                });
    }

    /**
     * 权限结果处理
     */
    private PermissionUtils mPermissionUtils = new PermissionUtils() {
        @Override
        protected void onPermissionResult(boolean isSuccess, Permission permission) {
            if (permission.name.equalsIgnoreCase(mNeedPermission[0])) {
                mPermissionUtils.requestPermission(mNeedPermission[1]);
            } else if (permission.name.equalsIgnoreCase(mNeedPermission[1])) {
                mPermissionUtils.requestPermission(mNeedPermission[2]);
            } else if (permission.name.equalsIgnoreCase(mNeedPermission[2])) {
                startNextStep();
            }
        }
    };

}
