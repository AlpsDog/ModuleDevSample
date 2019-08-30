package com.wonly.lib_base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_base.base.CatalogParam;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: HSL
 * @Time: 2019/8/29 15:54
 * @E-mail: xxx@163.com
 * @Description: 创建目录工具类~
 */
public class CatalogUtils {

    private static CatalogParam catalogParam = null;
    public static OnCatalogInitListener onCatalogInitListener = null;

    public static void setOnCatalogInitListener(OnCatalogInitListener listener) {
        onCatalogInitListener = listener;
    }

    public interface OnCatalogInitListener {
        CatalogParam getStorageInit();
    }

    private static boolean initParam() {
        if (catalogParam == null) {
            if (onCatalogInitListener == null) {
                return false;
            }
            catalogParam = onCatalogInitListener.getStorageInit();
            if (catalogParam == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * sd卡目录下，创建应用主目录
     * 如果sd卡不存在，则在内部存储创建主目录
     * return
     */
    private static File getAppCatalog() {
        if (SDCardUtils.hasSDCard()) {
            //Environment.getExternalStorageDirectory()
            //获取到的路径是: /storage/emulated/0。
            //这是SD卡根路径，6.0后写入需要用户授权。
            //在此路径下的文件是app独立文件，是完全开放的，对于其他应用程序或者用户都可以访问，
            //当你的应用被卸载的时候这部分文件也不会被删除。
            Logger.d(Environment.getExternalStorageDirectory());
            return createDirectory(Environment.getExternalStorageDirectory(), catalogParam.getAppDir());
        } else {
            //没有外部存储，则建于内部存储
            // /data/user/0/packname/app_myFile
            File root = BaseApplication.getInstance().getDir(catalogParam.getAppDir(), Context.MODE_PRIVATE);
            return createDirectory(root);
        }
    }

    /**
     * 获取应用主目录
     *
     * @return
     */
    public static File getRootDir() {
        if (!initParam()) {
            return null;
        }
        return getAppCatalog();
    }

    /**
     * 获取目录下的子目录 如果不存在，则自动创建
     * <p>
     * param dirname
     * return
     */
    private static File getDir(String dirname) {
        return createDirectory(getAppCatalog(), dirname);
    }

    /**
     * 获取图片目录
     * <p>
     * return
     */
    public static File getImageDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getImagesDir());
    }

    /**
     * 获取下载目录
     * <p>
     * return
     */
    public static File getDownloadDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getDownload());
    }

    /**
     * 获取音频文件目录
     * <p>
     * return
     */
    public static File getAudioDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getAudiosDir());
    }

    /**
     * 获取视频文件目录
     * <p>
     * return
     */
    public static File getVideoDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getVideoDir());
    }

    /**
     * 获取错误日志目录
     * <p>
     * return
     */
    public static File getErrorDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getErrorsDir());
    }

    /**
     * 获取Info日志目录
     * <p>
     * return
     */
    public static File getInfoDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getInfosDir());
    }

    /**
     * 获取二维码目录
     * <p>
     * return
     */
    public static File getQRCodeDir() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getQrcodesDir());
    }

    /**
     * return
     */
    public static File getOssRecord() {
        if (!initParam()) {
            return null;
        }
        return getDir(catalogParam.getOssRecord());
    }

    /**
     * 创建目录
     * <p>
     * param dir  主目录
     * param dest 需要创建的子目录
     * return
     */
    public static File createDirectory(File dir, String dest) {
        File result = new File(dir, File.separator + dest + File.separator);
        if (!result.exists()) {
            result.mkdirs();
        }
        return result;
    }

    /**
     * 创建目录
     *
     * @param dir
     * @return
     */
    public static File createDirectory(File dir) {
        File result = new File(dir, File.separator);
        if (!result.exists()) {
            result.mkdirs();
        }
        return result;
    }

    /**
     * 保存Bitmap
     * <p>
     * param dir      目录
     * param filename 文件名
     * param bt       图片
     * return
     */
    public static File saveBitmap(File dir, String filename, Bitmap bt) {
        try {
            File mfile = new File(dir, filename);
            if (mfile.exists()) {
                mfile.delete();
            }
            mfile.createNewFile();
            FileOutputStream out = new FileOutputStream(mfile);
            bt.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            return mfile;
        } catch (Exception e) {
            Logger.e("save bitmap error:%s", e.getMessage());
        }
        return null;
    }
}
