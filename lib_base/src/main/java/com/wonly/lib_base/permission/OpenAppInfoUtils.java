package com.wonly.lib_base.permission;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.orhanobut.logger.Logger;

/**
 * @Description:打开应用程序信息界面
 */
public class OpenAppInfoUtils {

    /**
     * 硬件制造商
     */
    private static final String MARK = Build.MANUFACTURER.toLowerCase();
    public static final int APP_INFO_REQUEST_CODF = 0X66;

    public static void openAppInfo(Activity activity) {
        try {
            if (MARK.contains("huawei")) {
                huaWeiApi(activity);
            } else if (MARK.contains("xiaomi")) {
                xiaomiApi(activity);
            } else if (MARK.contains("oppo")) {
                oppoApi(activity);
            } else if (MARK.contains("vivo")) {
                vivoApi(activity);
            } else if (MARK.contains("samsung")) {
                samsungApi(activity);
            } else if (MARK.contains("meizu")) {
                meizuApi(activity);
            } else if (MARK.contains("smartisan")) {
                smartisanApi(activity);
            } else {
                defaultApi(activity);
            }
        } catch (Exception e) {
            Logger.e("打开设置界面异常：%s", e.getMessage());
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            activity.startActivity(intent);
        }
    }

    /**
     * App details page.
     */
    private static void defaultApi(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivityForResult(intent, APP_INFO_REQUEST_CODF);
    }

    /**
     * Huawei cell phone Api23 the following method.
     */
    private static void huaWeiApi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            defaultApi(activity);
        } else {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            activity.startActivityForResult(intent, APP_INFO_REQUEST_CODF);
        }
    }

    /**
     * Xiaomi phone to achieve the method.
     */
    private static void xiaomiApi(Activity activity) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", activity.getPackageName());
        activity.startActivityForResult(intent, APP_INFO_REQUEST_CODF);
    }

    /**
     * Vivo phone to achieve the method.
     */
    private static void vivoApi(Activity activity) {
        Intent intent = new Intent();
        intent.putExtra("packagename", activity.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
        } else {
            intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        }
        activity.startActivityForResult(intent, APP_INFO_REQUEST_CODF);
    }

    /**
     * Oppo phone to achieve the method.
     */
    private static void oppoApi(Activity activity) {
        defaultApi(activity);
    }

    /**
     * Meizu phone to achieve the method.
     */
    private static void meizuApi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            defaultApi(activity);
        } else {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.putExtra("packageName", activity.getPackageName());
            intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
            activity.startActivityForResult(intent, APP_INFO_REQUEST_CODF);
        }
    }

    /**
     * Smartisan phone to achieve the method.
     */
    private static void smartisanApi(Activity activity) {
        defaultApi(activity);
    }

    /**
     * Samsung phone to achieve the method.
     */
    private static void samsungApi(Activity activity) {
        defaultApi(activity);
    }
}
