package com.wonly.lib_base.permission;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.wonly.lib_base.base.BaseActivity;
import com.wonly.lib_base.base.BaseFragment;
import com.wonly.lib_base.utils.ObjectUtils;
import com.wonly.lib_base.utils.ToastUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @Author: HSL
 * @Time: 2019/9/4 14:55
 * @E-mail: xxx@163.com
 * @Description: 权限请求
 */
public class PermissionUtils {

    private BaseActivity mActivity;
    private RxPermissions mRxPermissions;

    public PermissionUtils() {
        // TODO: 2018/10/10
    }

    public void initRxPermission(BaseActivity activity) {
        mActivity = activity;
        mRxPermissions = new RxPermissions(activity);
    }

    public void initRxPermission(BaseFragment fragment) {
        mActivity = (BaseActivity) fragment.getActivity();
        mRxPermissions = new RxPermissions(fragment);
    }

    /**
     * 请求权限
     *
     * @param permissions
     */
    public void requestPermission(String... permissions) {
        if (ObjectUtils.isEmpty(mRxPermissions)) {
            ToastUtils.showShort("权限请求失败!");
            return;
        }
        Disposable subscribe = mRxPermissions
                .requestEach(permissions)
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        rxPermissionResult(permission);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable.getMessage());
                    }
                });
    }

    private void rxPermissionResult(Permission permission) {
        if (permission.granted) {
            //权限通过
            onPermissionResult(true, permission);
        } else if (permission.shouldShowRequestPermissionRationale) {
            //权限被拒绝过一次,再次申请的解释说明
            showPermissionExplain(permission);
        } else {
            //拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限
            openPermissionSetting(permission);
        }
    }

    /**
     * 权限获取结果
     *
     * @param isSuccess
     * @param permission
     */
    protected void onPermissionResult(boolean isSuccess, Permission permission) {

    }

    /**
     * 权限被拒绝过一次,再次申请的解释说明
     *
     * @param permission
     */
    private void showPermissionExplain(Permission permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("提示");
        String permissionName = TransformUtils.transformText(mActivity, permission.name);
        String msg = String.format("请允许%s,否则将影响应用的使用", permissionName);
        builder.setMessage(msg);
        builder.setPositiveButton("允许", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermission(permission.name);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPermissionResult(false, permission);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    /**
     * 打开权限设置界面
     *
     * @param permission
     */
    private void openPermissionSetting(Permission permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("提示");
        String permissionName = TransformUtils.transformText(mActivity, permission.name);
        String msg = String.format("您已经拒绝%s,请前往应用设置界面打开权限", permissionName);
        builder.setMessage(msg);
        builder.setPositiveButton("前往", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OpenAppInfoUtils.openAppInfo(mActivity);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPermissionResult(false, permission);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

}
